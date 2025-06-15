package com.dr7.dr7.domain.vo.produtodto;

import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import jakarta.validation.constraints.NotNull;

public record CategoriaResponse (@NotNull Long id, String nome){
    public CategoriaResponse(CategoriaProdutos categoria) {
        this(categoria.getIdCategoria(),categoria.getNome());
    }
}
