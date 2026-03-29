package com.kriss.user.controller;

import com.kriss.user.dto.AuthRequest;
import com.kriss.user.dto.AuthResponse;
import com.kriss.user.entity.Users;
import com.kriss.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Inscription et connexion des utilisateurs")
public class AuthController {

    private final UserService userService;

    @Operation(
            summary = "Inscription",
            description = "Crée un nouvel utilisateur avec email et mot de passe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Utilisateur créé",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Email déjà utilisé")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        try {
            Users user = userService.register(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getEmail(), "Inscription réussie"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(
            summary = "Connexion",
            description = "Connecte un utilisateur existant avec email et mot de passe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Connexion réussie",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            Users user = userService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getEmail(), "Connexion réussie"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
