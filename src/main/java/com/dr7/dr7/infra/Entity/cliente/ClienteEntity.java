package com.dr7.dr7.infra.Entity.cliente;

import com.dr7.dr7.infra.Entity.EnderecoEntity;
import com.dr7.dr7.infra.Entity.pedidos.PedidosEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;
    private String nome;
    private String phoneNumer;
    private String cpf;
    private String datanascimento;
    private LocalDateTime createdTime;
    private String sexo;
    @OneToOne
    private EnderecoEntity endereco;
    private String img;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<PedidosEntity> pedidos;

}
