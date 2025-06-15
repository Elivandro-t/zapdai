package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.empresas.EmpresaComProdutoDTO;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;
import com.dr7.dr7.domain.vo.RespostaEmpresa;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityRepository {
     EmpresaRespostaDTO registroEmpresarial(EmpresaDTO empresa);
     List<EmpresaDTO> lista();
     List<RespostaEmpresa> find();
     Page<EmpresaComProdutoDTO> findOndeProdutoCategoria(Long idCategoria, Pageable page);

}
