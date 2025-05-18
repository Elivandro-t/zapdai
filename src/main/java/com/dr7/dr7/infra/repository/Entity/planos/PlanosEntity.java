package com.dr7.dr7.infra.repository.Entity.planos;

import com.dr7.dr7.domain.planos.Planos;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "planos")
public class PlanosEntity {
    @Id
    private String id;
    private String title;
    private Double price;
    private String description;
    private LocalDateTime dataCreate;
    private String  promocao;
    private String subDescricaoPermition;
    private boolean ativo;
    private boolean permiteDeletarProduto;
    private boolean permiteRelatorios;
    private boolean permiteAdicionarFuncionarios;
     public PlanosEntity(Planos planos){
         this.title = planos.getTitle();
         this.price = planos.getPrice();
         this.description = planos.getDescription();
         this.promocao = planos.getPromocao();
         this.subDescricaoPermition = planos.getSubDescricaoPermition();
     }
    public PlanosEntity(){}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public boolean isPermiteAdicionarFuncionarios() {
        return permiteAdicionarFuncionarios;
    }

    public void setPermiteAdicionarFuncionarios(boolean permiteAdicionarFuncionarios) {
        this.permiteAdicionarFuncionarios = permiteAdicionarFuncionarios;
    }

    @PrePersist
    public void gerarId() {
        if (id == null) {
            long timestamp = System.currentTimeMillis();
            int random = new Random().nextInt(99999);
            this.id = "zapdai_current_id-" + timestamp + "-" + random;
        }

        if (dataCreate == null) {
            this.dataCreate = java.time.LocalDateTime.now();
        }
    }

    public boolean isPermiteRelatorios() {
        return permiteRelatorios;
    }

    public void setPermiteRelatorios(boolean permiteRelatorios) {
        this.permiteRelatorios = permiteRelatorios;
    }

    public boolean isPermiteDeletarProduto() {
        return permiteDeletarProduto;
    }

    public void setPermiteDeletarProduto(boolean permiteDeletarProduto) {
        this.permiteDeletarProduto = permiteDeletarProduto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(LocalDateTime dataCreate) {
        this.dataCreate = dataCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public String getPromocao() {
        return promocao;
    }

    public String getSubDescricaoPermition() {
        return subDescricaoPermition;
    }

    public void setSubDescricaoPermition(String subDescricaoPermition) {
        this.subDescricaoPermition = subDescricaoPermition;
    }
}
