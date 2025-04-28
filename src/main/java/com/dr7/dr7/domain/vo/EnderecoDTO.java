package com.dr7.dr7.domain.vo;

import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;

public record EnderecoDTO(String numeroEndereco, String latLong, String rua, String logradouro) {
    public EnderecoDTO(EnderecoEntity endereco) {
        this(endereco.getNumeroEndereco(),endereco.getLatLong(),endereco.getRua(),endereco.getLogradouro());
    }
}
