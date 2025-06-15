package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.categorias.Categorias;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
       @NotBlank String nome,
       @NotBlank String cor,
        String icone
)
{
    public CategoriaDTO(Categorias e) {
        this(e.getNome(),e.getCor(),e.getIcone());
    }


}
