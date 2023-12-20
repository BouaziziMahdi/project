package com.projectIntegration.authentification.service;

import com.projectIntegration.authentification.config.JwtService;
import com.projectIntegration.authentification.dto.AuthenticationRequest;
import com.projectIntegration.authentification.dto.AuthenticationResponse;
import com.projectIntegration.authentification.dto.Userdto;
import com.projectIntegration.authentification.entity.Role;
import com.projectIntegration.authentification.entity.TypeRole;
import com.projectIntegration.authentification.entity.User;
import com.projectIntegration.authentification.repositories.RoleRepository;
import com.projectIntegration.authentification.repositories.UserRepository;
import com.projectIntegration.authentification.validator.ObjectsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository useruserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<Userdto> validator;

    @Transactional
    public AuthenticationResponse register(Userdto userdto) {
        validator.validate(userdto);
        var user = User.builder()
                .firstName(userdto.getFirstName())
                .lastName(userdto.getLastName())
                .email(userdto.getEmail())
                .password(
                        passwordEncoder.encode(userdto.getPassword())
                )
                .build();
        // set roles
        var userRole = roleRepository.findByName(TypeRole.USER.name())
                .orElse(
                        Role.builder()
                                .name(TypeRole.USER.name())
                                .build()
                );
        if (userRole.getId() == null) {
            userRole = roleRepository.save(userRole);
        }
        var defaultUserRole = List.of(userRole);
        user.setRoles(defaultUserRole);
        var savedUser = useruserRepository.save(user);

        userRole.setUsers(new ArrayList<>(List.of(savedUser)));
        roleRepository.save(userRole);
        var claims = new HashMap<String, Object>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());
        var jwtToken = jwtService.generateToken(savedUser, claims);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(String.valueOf(savedUser.getId()))
                .username(savedUser.getFirstName() + " " + savedUser.getLastName())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = useruserRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var claims = new HashMap<String, Object>();
        claims.put("role", user.getRoles());
        claims.put("active", user.isActive());
        var jwtToken = jwtService.generateToken(user, claims);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(String.valueOf(user.getId()))
                .username(user.getFirstName() + " " + user.getLastName())
                .build();
    }
}