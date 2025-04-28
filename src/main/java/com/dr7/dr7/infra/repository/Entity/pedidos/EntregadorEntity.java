package com.dr7.dr7.infra.repository.Entity.pedidos;

import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import jakarta.persistence.*;

import java.util.List;

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
    @OneToMany(mappedBy = "entregadorPedido",cascade = CascadeType.ALL)
    private List<ItensPedidos> itensPedidos;

}
