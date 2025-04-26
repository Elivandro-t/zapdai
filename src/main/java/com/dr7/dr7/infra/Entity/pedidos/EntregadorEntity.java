package com.dr7.dr7.infra.Entity.pedidos;

import com.dr7.dr7.infra.Entity.EnderecoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "entregador")
public class EntregadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String placaVeiculo;
    private String modelo;
    private String anoVeiculo;
    private String NomeEntregador;
    private String Aprovacao;
    private boolean Ativo = false;
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;
    private String StatusPagamento;
    private Float valorAPagar;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "entregadorRef")
    private ItensPedidos itensPedidos;

}
