package com.dr7.dr7.infra.repository.Entity.produtosEntity;

import com.dr7.dr7.domain.categorias.Categorias;
import com.dr7.dr7.domain.vo.CategoriaDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CategoriaProdutos")
public class CategoriaProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoria;
    private String nome;
    private String cor;
    private String icone;
    @OneToMany(mappedBy = "categoria")
    List<ProdutosEntity> produtos;

    public CategoriaProdutos(Categorias categoria) {
         this.nome = categoria.getNome();
        this.cor = categoria.getCor();
        this.icone = categoria.getIcone();
    }
    public CategoriaProdutos( ) {
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

    public long getIdCategoria() {
        return idCategoria;
    }
}
