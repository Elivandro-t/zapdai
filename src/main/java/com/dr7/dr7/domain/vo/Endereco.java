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
    private String logradouro;

    public Endereco(EnderecoDTO endereco) {
       if(endereco!=null){
           this.numeroEndereco = endereco.numeroEndereco();
           this.latLong = endereco.latLong();
           this.logradouro = endereco.logradouro();
           this.cep = endereco.cep();
           this.bairro = endereco.bairro();
           this.cidade = endereco.cidade();
           this.estado_sigla = endereco.estado_sigla();
           this.logradouro = endereco.logradouro();
       }
    }
    public Endereco() {

    }

    public Endereco(EnderecoEntity endereco) {
        if(endereco!=null) {
            this.numeroEndereco = endereco.getNumeroEndereco();
            this.estado_sigla = endereco.getEstado_sigla();
            this.cep = endereco.getCep();
            this.cidade = endereco.getCidade();
            this.latLong = endereco.getLatLong();
            this.bairro = endereco.getBairro();
            this.logradouro = endereco.getLogradouro();

        }
    }


    public String getEstado_sigla() {
        return estado_sigla;
    }

    public String getLogradouro() {
        return logradouro;
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
