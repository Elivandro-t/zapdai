package com.dr7.dr7.domain.vo.cliente;

import jakarta.validation.constraints.NotBlank;

public record BuscaUsuario(String nome, @NotBlank String cpf, String email) {
}
