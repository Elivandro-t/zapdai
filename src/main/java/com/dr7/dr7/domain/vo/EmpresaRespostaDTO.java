package com.dr7.dr7.domain.vo;

import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.Status;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public record EmpresaRespostaDTO(String nomeCompania,
                                 String razaoSocial,
                                 String numeroDeTelefone ,
                                 EnderecoDTO endereco,
                                 String email,LocalTime
                                 dataCriacao,Status status ) {
    public EmpresaRespostaDTO(EmpresaEntity e) {
        this(e.getNomeCompania(),e.getRazaoSocial(),e.getNumeroDeTelefone(),new EnderecoDTO(e.getEndereco()),e.getEmail(),e.getDataCriacao(),e.getStatus());
    }
}
