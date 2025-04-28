package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;

import java.util.List;

public interface EntityRepository {
     EmpresaRespostaDTO registroEmpresarial(EmpresaDTO empresa);
     List<EmpresaDTO> lista();
}
