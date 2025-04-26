package com.dr7.dr7.infra.Entity.produtosEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "CategoriaProdutos")
public class CategoriaProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoria;
    private String nome;
    private String cor;
    private String empresaRef;
    private long FilialRef;
    private String icone;

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public String getEmpresaRef() {
        return empresaRef;
    }

    public long getFilialRef() {
        return FilialRef;
    }

    public String getIcone() {
        return icone;
    }
}
