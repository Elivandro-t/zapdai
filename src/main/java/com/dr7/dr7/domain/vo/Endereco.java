package com.dr7.dr7.domain.vo;

public class Endereco {
    private long id;
    private String numeroEndereco;
    private String latLong;
    private String rua;
    private String logradouro;

    public Endereco(EnderecoDTO endereco) {
        this.numeroEndereco = endereco.numeroEndereco();
        this.latLong = endereco.latLong();
        this.rua = endereco.rua();
        this.logradouro = endereco.logradouro();
    }
    public Endereco() {}
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

}
