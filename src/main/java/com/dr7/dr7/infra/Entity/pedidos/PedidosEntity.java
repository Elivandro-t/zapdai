package com.dr7.dr7.infra.Entity.pedidos;

import com.dr7.dr7.infra.Entity.cliente.ClienteEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Pedidos")
public class PedidosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long empresaId;
    private StatusPagamentos statusPamentos;
    private Float preco;
    private String cuponDesconto;
    private Float precoDesconto;
    private Float precoTotal;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItensPedidos> itens;
    private String statusUsurio;
    @ManyToOne
    private ClienteEntity cliente;



}
