package com.dr7.dr7.infra.repository.Entity.produtosEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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

    public CategoriaProdutos(@NotNull Long aLong) {
    }

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
