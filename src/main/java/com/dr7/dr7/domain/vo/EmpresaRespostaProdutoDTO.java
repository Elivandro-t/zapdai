package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.domain.vo.produtodto.UsuarioVO;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.validation.constraints.NotBlank;

public record EmpresaRespostaProdutoDTO( String nomeCompania,
                                         String cpfCnpj,
                                         String numeroDeTelefone,
                                         String email) {
    public EmpresaRespostaProdutoDTO(Empresa e) {
        this(e.getNomeCompania(),e.getCpfCnpj().replaceAll("\\d{3}$", "***"),e.getNumeroDeTelefone(),e.getEmail());

    }
}
