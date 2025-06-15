package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.vo.cliente.UsuarioDTO;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.Status;

import java.time.LocalTime;

public record EmpresaRespostaDTO(String nomeCompania,
                                 String cpfCnpj,
                                 String numeroDeTelefone ,
                                 EnderecoDTO endereco,
                                 String email,LocalTime
                                 dataCriacao,Status status,
                                 UsuarioDTO propietario
) {
    public EmpresaRespostaDTO(EmpresaEntity e) {
        this(e.getNomeCompania(),e.getCpfCnpj(),e.getNumeroDeTelefone(),new EnderecoDTO(e.getEndereco()),e.getEmail(),e.getDataCriacao(),e.getStatus(),new UsuarioDTO(e.getResposavel()));
    }
}
