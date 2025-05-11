package com.dr7.dr7.domain.planos;

import com.dr7.dr7.domain.vo.planosDTO.PlanosDTO;
import com.dr7.dr7.infra.repository.Entity.planos.PlanosEntity;

import java.time.LocalDateTime;

public class Planos {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private LocalDateTime dataCreate;
    private boolean ativo;
    private boolean permiteDeletarProduto;
    private boolean permiteRelatorios;
    private boolean permiteAdicionarFuncionarios;
    private  String promocao;
    private String subDescricaoPermition;

    public Planos(PlanosDTO plano) {
        this.title = plano.title();
        this.price = plano.price();
        this.dataCreate = LocalDateTime.now();
        this.description = plano.description();
        this.ativo = true;

    }

    public Planos(PlanosEntity plano) {
        this.title = plano.getTitle();
        this.price = plano.getPrice();
        this.dataCreate = plano.getDataCreate();
        this.description = plano.getDescription();
        this.promocao = plano.getPromocao();
        this.subDescricaoPermition = plano.getSubDescricaoPermition();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(LocalDateTime dataCreate) {
        this.dataCreate = dataCreate;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isPermiteDeletarProduto() {
        return permiteDeletarProduto;
    }

    public void setPermiteDeletarProduto(boolean permiteDeletarProduto) {
        this.permiteDeletarProduto = permiteDeletarProduto;
    }

    public boolean isPermiteRelatorios() {
        return permiteRelatorios;
    }

    public void setPermiteRelatorios(boolean permiteRelatorios) {
        this.permiteRelatorios = permiteRelatorios;
    }

    public boolean isPermiteAdicionarFuncionarios() {
        return permiteAdicionarFuncionarios;
    }

    public void setPermiteAdicionarFuncionarios(boolean permiteAdicionarFuncionarios) {
        this.permiteAdicionarFuncionarios = permiteAdicionarFuncionarios;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public String getSubDescricaoPermition() {
        return subDescricaoPermition;
    }

    public void setSubDescricaoPermition(String subDescricaoPermition) {
        this.subDescricaoPermition = subDescricaoPermition;
    }
}

