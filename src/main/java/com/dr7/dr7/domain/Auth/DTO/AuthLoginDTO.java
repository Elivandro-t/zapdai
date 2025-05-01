package com.dr7.dr7.domain.Auth.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginDTO(@NotBlank String email, @NotBlank String password) {
}
