package com.dr7.dr7.domain.vo;

import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;

public class Endereco {
    private long id;
    private String cep;
    private String numeroEndereco;
    private String bairro;
    private String cidade;
    private String latLong;
    private String rua;
    private String logradouro;

    public Endereco(EnderecoDTO endereco) {
        this.numeroEndereco = endereco.numeroEndereco();
        this.latLong = endereco.latLong();
        this.rua = endereco.rua();
        this.logradouro = endereco.logradouro();
        this.cep = endereco.cep();
       this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado_sigla = endereco.estado_sigla();
    }
    public Endereco() {

    }

    public Endereco(EnderecoEntity endereco) {
    }


    public String getEstado_sigla() {
        return estado_sigla;
    }

    public String getLogradouro() {
        return logradouro;
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

    public long getId() {
        return id;
    }

    private String estado_sigla;

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

}
