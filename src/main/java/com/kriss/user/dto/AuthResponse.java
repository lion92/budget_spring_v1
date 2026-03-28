package com.kriss.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Réponse après connexion / inscription")
public class AuthResponse {

    @Schema(description = "ID de l'utilisateur", example = "1")
    private Long id;

    @Schema(description = "Email de l'utilisateur", example = "test@email.com")
    private String email;

    @Schema(description = "Message de statut", example = "Login successful")
    private String message;
}
