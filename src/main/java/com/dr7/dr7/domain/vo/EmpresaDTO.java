package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.domain.vo.produtodto.UsuarioVO;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpresaDTO(@NotBlank String nomeCompania,@NotBlank String cpfCnpj,@NotBlank String numeroDeTelefone , EnderecoDTO endereco,@NotBlank  String email,UsuarioVO usuario,@NotBlank(message = "O plano é obrigatório")
String planoId ) {
    public EmpresaDTO(EmpresaEntity e) {
        this(e.getNomeCompania(),e.getCpfCnpj(),e.getNumeroDeTelefone(),new EnderecoDTO(e.getEndereco()),e.getEmail(),null,"");
    }

    public EmpresaDTO(Empresa e) {
        this(e.getNomeCompania(),e.getCpfCnpj(),e.getNumeroDeTelefone(),new EnderecoDTO(e.getEndereco()),e.getEmail(),null,"");

    }
}
