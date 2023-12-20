package com.projectIntegration.authentification.service;

import com.projectIntegration.authentification.dto.Userdto;
import com.projectIntegration.authentification.entity.User;
import com.projectIntegration.authentification.repositories.UserRepository;
import com.projectIntegration.authentification.validator.ObjectsValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository ;
    private final ObjectsValidator<Userdto> validator ;
    private final Userdto userDto ;
    private final PasswordEncoder passwordEncoder ;




    public Integer create(Userdto userdto) {
        validator.validate(userdto);
        var user = Userdto.toEntity(userdto);
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        return userRepository.save(user).getId();
    }



    public List<Userdto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(Userdto::fromEntity)
                .collect(Collectors.toList());
    }

    public Userdto findById(Integer id) {
        return userRepository.findById(id)
                .map(Userdto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user found with the ID:: " + id));
    }



    public Integer update(Integer id, Userdto userDto) {
        validator.validate(userDto);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with the ID:: " + id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return user.getId();
    }
      public Integer validateAccount(Integer userId) {
          var user = userRepository.findById( userId )
                  .orElseThrow(() -> new EntityNotFoundException("No user found with the ID for account validation:: " + userId));

          user.setActive(true);
          userRepository.save(user);
          return user.getId();
      }

    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));

        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
    public List<Userdto> findAllUsersByState(boolean active) {
        return userRepository.findAllByActive( active )
                .stream()
                .map(Userdto::
                        fromEntity)
                .collect(Collectors.toList());
    }
}