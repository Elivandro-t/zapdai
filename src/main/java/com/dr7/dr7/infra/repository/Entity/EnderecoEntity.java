package com.dr7.dr7.infra.repository.Entity;

import com.dr7.dr7.domain.vo.Endereco;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numeroEndereco;
    private String latLong;
    private String logradouro;
    private String estado_sigla;
    private String cep;
    private String bairro;
    private String cidade;
    public EnderecoEntity(Endereco endereco) {
        if (endereco != null) {
            this.numeroEndereco = endereco.getNumeroEndereco();
            this.latLong = endereco.getLatLong();
            this.logradouro = endereco.getLogradouro();
            this.estado_sigla = endereco.getEstado_sigla();
            this.cep = endereco.getCep();
            this.bairro = endereco.getBairro();
            this.cidade = endereco.getCidade();
        }
    }
    public EnderecoEntity() {}

    public EnderecoEntity(EnderecoDTO e) {
        this.numeroEndereco = e.numeroEndereco();
        this.latLong = e.latLong();
        this.logradouro = e.logradouro();
        this.estado_sigla = e.estado_sigla();
        this.cep = e.cep();
        this.bairro = e.bairro();
        this.cidade = e.cidade();
    }

    public long getId() {
        return id;
    }

    public String getEstado_sigla() {
        return estado_sigla;
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

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void atualiza(EnderecoDTO enderecoDTO) {
        this.numeroEndereco = enderecoDTO.numeroEndereco();
        this.logradouro = enderecoDTO.logradouro();
        this.latLong = enderecoDTO.latLong();
        this.estado_sigla = enderecoDTO.estado_sigla();
        this.cep = enderecoDTO.cep();
        this.bairro = enderecoDTO.bairro();
        this.cidade = enderecoDTO.cidade();
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEstado_sigla(String estado_sigla) {
        this.estado_sigla = estado_sigla;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }
}
