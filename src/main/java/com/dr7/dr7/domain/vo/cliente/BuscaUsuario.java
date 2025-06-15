package com.dr7.dr7.domain.vo.cliente;

import jakarta.validation.constraints.NotBlank;

public record BuscaUsuario(String nome, String cpf,@NotBlank(message = "Informe o email") String email) {
}
