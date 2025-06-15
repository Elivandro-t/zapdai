package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.empresas.EmpresaComProdutoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoEmpresaDTO;
import com.dr7.dr7.domain.vo.produtodto.UsuarioVO;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

public record RespostaEmpresa(
        String idEmpresa,
         String nomeCompania,
         String numeroDeTelefone ,
//         EnderecoDTO endereco,
         String email,
        List<ProdutoEmpresaDTO> produtos

) {
    public static RespostaEmpresa from(EmpresaEntity e) {
        if (e.getProdutos() == null || e.getProdutos().isEmpty()) {
            return null; // ou Optional.empty(), se preferir
        }
        return new RespostaEmpresa(
                e.getIdEmpresa(),
                e.getNomeCompania(),
                e.getNumeroDeTelefone(),
                e.getEmail(),
                e.getProdutos().stream().map(ProdutoEmpresaDTO::new).toList()
        );
    }

}
