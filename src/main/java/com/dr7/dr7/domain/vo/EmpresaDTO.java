package com.dr7.dr7.domain.vo;

import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.Status;
import jakarta.validation.constraints.NotBlank;

public record EmpresaDTO(@NotBlank String nomeCompania,@NotBlank String razaoSocial,@NotBlank String numeroDeTelefone , EnderecoDTO endereco, @NotBlank String email) {
    public EmpresaDTO(EmpresaEntity e) {
        this(e.getNomeCompania(),e.getRazaoSocial(),e.getNumeroDeTelefone(),new EnderecoDTO(e.getEndereco()),e.getEmail());
    }
}
