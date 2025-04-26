package com.dr7.dr7.infra.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numeroEndereco;
    private String latLong;
    private String rua;
    private String logradouro;
    private String estado_sigla;
}
