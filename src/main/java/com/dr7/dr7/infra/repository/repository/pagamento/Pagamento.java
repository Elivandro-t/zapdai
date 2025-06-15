package com.dr7.dr7.infra.repository.repository.pagamento;

import com.dr7.dr7.application.services.amqp.PagamentoDTo;
import com.dr7.dr7.application.services.amqp.StatusPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name="pagamentos")
public class Pagamento {
    @Id
    private String id;
    StatusPagamento statusPagoPlano;
    boolean statusPago;
    String dateCreated;
    String planoId;
    String planoName;
    String PagamentoRef;
    String userName;
    String email;
    BigDecimal valorPago;
    String formaDePagamento;
    LocalDateTime dataInicio;
    LocalDateTime dataFim;

    public Pagamento(PagamentoDTo pagamentoRef) {

        this.formaDePagamento = pagamentoRef.formaDePagamento();
        this.valorPago = pagamentoRef.valorPago();
        this.email = pagamentoRef.email();
        this.userName = pagamentoRef.userName();
        PagamentoRef = pagamentoRef.PagamentoRef();
        this.planoName = pagamentoRef.planoName();
        this.planoId = pagamentoRef.planoId();
        this.dateCreated = pagamentoRef.dateCreated();
        this.statusPago = pagamentoRef.statusPago();
        this.statusPagoPlano = pagamentoRef.statusPagoPlano();
        this.dataInicio = LocalDateTime.now();
        if (pagamentoRef.planoName().equalsIgnoreCase("Zapdai Start")) {
            this.dataFim = this.dataInicio.plusHours(1);
        } else {
            this.dataFim = this.dataInicio.plusHours(2);
        }
    }
    public Pagamento(){}

    @PrePersist
    public void gerarId() {
        if (id == null) {
            long timestamp = System.currentTimeMillis();
            int random = new Random().nextInt(99999);
            this.id = "zapdai_pagamento_id-" + timestamp + "-" + random;
        }
    }


    public StatusPagamento getStatusPagoPlano() {
        return statusPagoPlano;
    }

    public void setStatusPagoPlano(StatusPagamento statusPagoPlano) {
        this.statusPagoPlano = statusPagoPlano;
    }

    public boolean isStatusPago() {
        return statusPago;
    }

    public void setStatusPago(boolean statusPago) {
        this.statusPago = statusPago;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPlanoId() {
        return planoId;
    }

    public void setPlanoId(String planoId) {
        this.planoId = planoId;
    }

    public String getPlanoName() {
        return planoName;
    }

    public void setPlanoName(String planoName) {
        this.planoName = planoName;
    }

    public String getPagamentoRef() {
        return PagamentoRef;
    }

    public void setPagamentoRef(String pagamentoRef) {
        PagamentoRef = pagamentoRef;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
