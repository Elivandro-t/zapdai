package com.dr7.dr7.infra.Entity.pedidos;

import jakarta.persistence.*;

@Entity
@Table(name = "itens")
public class ItensPedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Integer quantidade;
    private long clienteId;
    private long empresaId;
    private String status;
    @ManyToOne
    private EntregadorEntity entregadorPedido;
}
