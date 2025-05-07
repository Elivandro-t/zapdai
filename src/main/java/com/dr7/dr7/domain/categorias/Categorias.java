package com.dr7.dr7.domain.categorias;

import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;

public class Categorias {
    private long idCategoria;
    private String nome;
    private String cor;
    private String icone;
    public Categorias(CategoriaDTO categoria,String icon) {
        this.nome = categoria.nome();
        this.cor = categoria.cor();
        this.icone = icon;
    }

    public Categorias(CategoriaProdutos categoria) {
        this.nome = categoria.getNome();
        this.cor = categoria.getCor();
        this.icone = categoria.getIcone();
    }




    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public String getIcone() {
        return icone;
    }
}
