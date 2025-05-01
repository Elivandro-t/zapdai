package com.dr7.dr7.domain.vo;

import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpresaDTO(@NotBlank String nomeCompania,@NotBlank String razaoSocial,@NotBlank String numeroDeTelefone , EnderecoDTO endereco, @NotBlank String email,@NotNull  Long clienteId,@NotBlank String cpf) {
    public EmpresaDTO(EmpresaEntity e) {
        this(e.getNomeCompania(),e.getRazaoSocial(),e.getNumeroDeTelefone(),new EnderecoDTO(e.getEndereco()),e.getEmail(),null,"");
    }
}
