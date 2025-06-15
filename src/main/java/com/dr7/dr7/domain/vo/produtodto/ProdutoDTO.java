package com.dr7.dr7.domain.vo.produtodto;

import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdutoDTO(
                        @NotBlank String productName,
                         @NotBlank String idEmpresa,
                         @NotNull Float price,
                         @NotNull Float peso,
                         @NotBlank String description,
                         @NotNull Integer amountQTD,
                         CategoriaResponse categoria
) {

    public ProdutoDTO(ProdutosEntity e) {
        this(e.getProducName(),e.getIdEmpresa(),e.getPrice(),e.getPeso(),e.getDescricao(),e.getQuantidade(),new CategoriaResponse(e.getCategoria()));
    }
}
