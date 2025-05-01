package com.dr7.dr7.infra.repository.Entity;

import com.dr7.dr7.domain.vo.Endereco;
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
    private String cep;
    private String bairro;
    private String cidade;
    public EnderecoEntity(Endereco endereco) {
        this.numeroEndereco = endereco.getNumeroEndereco();
        this.latLong = endereco.getLatLong();
        this.rua = endereco.getRua();
        this.logradouro = endereco.getLogradouro();
        this.estado_sigla = endereco.getEstado_sigla();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
    }
    public EnderecoEntity() {}
    public long getId() {
        return id;
    }

    public String getEstado_sigla() {
        return estado_sigla;
    }

    public String getRua() {
        return rua;
    }

    public String getLatLong() {
        return latLong;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

}
