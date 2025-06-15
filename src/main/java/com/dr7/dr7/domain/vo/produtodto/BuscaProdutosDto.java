package com.dr7.dr7.domain.vo.produtodto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BuscaProdutosDto(@NotBlank(message = "Informe o campo NomeForcenedor") String nameEmpresa,@NotNull(message = "informome o id") String idEmpresa) {
}
