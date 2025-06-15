package com.dr7.dr7.domain.vo.produtodto;

import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoEmpresaDTO(
                          String idEmpresa,
                          String productName,
                          String imgUrl,
                          Float price,
                          Float peso,
                          String description,
                          Integer amountQTD,
                         CategoriaResponse categoria
) {

    public ProdutoEmpresaDTO(ProdutosEntity e) {
        this(e.getIdEmpresa(),e.getProducName(),e.getImgProduct(),e.getPrice(),e.getPeso(),e.getDescricao(),e.getQuantidade(),new CategoriaResponse(e.getCategoria()));
    }
}
