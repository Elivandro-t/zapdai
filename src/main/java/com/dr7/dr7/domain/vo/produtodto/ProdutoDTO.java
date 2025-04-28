package com.dr7.dr7.domain.vo.produtodto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
                         String imgProduct,
                        @NotBlank String productName,
                         @NotNull long fornecedoId,
                         @NotBlank String FornecedorName,
                         @NotNull Float price,
                         @NotNull Float peso,
                         @NotNull Long categoriaProduct,
                         @NotBlank String description,
                         @NotNull Integer amountQTD,
                         String categoriaProdutosName
) {
}
