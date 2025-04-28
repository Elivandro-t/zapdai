package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;
import com.dr7.dr7.gateways.EntityRepository;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpresaFactures implements EntityRepository {
   private EmpresaRepository repository;
   public EmpresaFactures(EmpresaRepository repository){
       this.repository = repository;
   }
    public EmpresaFactures(){}
    @Override
    public EmpresaRespostaDTO registroEmpresarial(EmpresaDTO empresa) {
      EmpresaEntity empresaEncontrada =  repository.findOneByRazaoSocial(empresa.razaoSocial());
       if(empresaEncontrada!=null){
           throw new RuntimeException("Empresa ja registrada!");
       }
        Empresa empresaModel = new Empresa(empresa);
      var resposta = repository.save(new EmpresaEntity(empresaModel));
       return new EmpresaRespostaDTO(resposta);
    }
    @Override
    public List<EmpresaDTO> lista() {
        return  repository.findAll().stream().map(EmpresaDTO::new).toList();
    }
}
