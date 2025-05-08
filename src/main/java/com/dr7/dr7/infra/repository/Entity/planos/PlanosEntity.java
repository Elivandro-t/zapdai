package com.dr7.dr7.infra.repository.Entity.planos;

import jakarta.persistence.*;

@Entity
@Table(name = "planos")
public class PlanosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valorMensal;
    private String descricao;
    // Você pode usar permissões booleanas ou listas de funcionalidades
    private boolean permiteDeletarProduto;
    private boolean permiteRelatorios;
    private boolean permiteAdicionarFuncionarios;
}
