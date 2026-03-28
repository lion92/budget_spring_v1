package com.kriss.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Données de connexion / inscription")
public class AuthRequest {

    @Schema(description = "Adresse email", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Mot de passe", example = "motdepasse123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
