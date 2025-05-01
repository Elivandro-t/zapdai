package com.dr7.dr7.domain.vo;

import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO( String numeroEndereco,
                           String latLong, String rua,
                           String logradouro,
                           String estado_sigla,
                           String cep,
                           String bairro,
                           String cidade

) {
    public EnderecoDTO(EnderecoEntity endereco) {
        this(endereco.getNumeroEndereco(),endereco.getLatLong(),endereco.getRua(),endereco.getLogradouro(),endereco.getEstado_sigla(),endereco.getCep(),endereco.getBairro(),endereco.getCidade());
    }

    public EnderecoDTO(Endereco endereco) {
        this(endereco.getNumeroEndereco(),endereco.getLatLong(),endereco.getRua(),endereco.getLogradouro(),endereco.getEstado_sigla(),endereco.getCep(),endereco.getBairro(),endereco.getCidade());

    }
}
