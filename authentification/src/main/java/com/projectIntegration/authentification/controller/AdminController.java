package com.projectIntegration.authentification.controller;
import com.projectIntegration.authentification.dto.Userdto;
import com.projectIntegration.authentification.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService service;
    @GetMapping("/users/inactive")
    public ResponseEntity<List<Userdto>> findAllNonActiveUsers() {
        return ResponseEntity.ok(service.findAllUsersByState(false));
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<Userdto>> findAllActiveUsers() {
        return ResponseEntity.ok(service.findAllUsersByState(true));
    }
}