package com.dr7.dr7.domain.vo.cliente;

import jakarta.validation.constraints.NotBlank;

public record VerificaCode(@NotBlank(message = "informe o email")  String email,@NotBlank(message = "Informe o codigo de verificação") String code) {
}
