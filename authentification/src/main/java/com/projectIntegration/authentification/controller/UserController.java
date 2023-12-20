package com.projectIntegration.authentification.controller;

import com.projectIntegration.authentification.dto.Userdto;
import com.projectIntegration.authentification.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService service;

    @Operation(
            description = "Saves a user to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully created"),
                    @ApiResponse(responseCode = "403", description = "Missing or invalid JWT token")
            }
    )

    @PostMapping
    public Integer save(
            @RequestBody Userdto userdto
    ) {
        return service.create(userdto);
    }

    @GetMapping
    public ResponseEntity<List<Userdto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            description = "Finds user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully created"),
                    @ApiResponse(responseCode = "403", description = "Missing or invalid JWT token")
            }
    )
    @GetMapping("/{user-id}")
    public ResponseEntity<Userdto> findById(
            @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/validate/{user-id}")
    public ResponseEntity<Integer> validateAccount(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.validateAccount(userId));
    }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.invalidateAccount(userId));
    }
    @PutMapping ("/{user-id}")
    public ResponseEntity<Integer> save(
            @RequestBody Userdto userDto , @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(service.update(userDto.getId(), userDto));
    }
}