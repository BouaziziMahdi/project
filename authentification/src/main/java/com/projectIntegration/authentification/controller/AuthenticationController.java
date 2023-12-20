package com.projectIntegration.authentification.controller;
import com.projectIntegration.authentification.dto.AuthenticationRequest;
import com.projectIntegration.authentification.dto.AuthenticationResponse;
import com.projectIntegration.authentification.dto.Userdto;

import com.projectIntegration.authentification.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag( name = "authentication")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Userdto userDto
    ) {
        return ResponseEntity.ok(service.register(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}