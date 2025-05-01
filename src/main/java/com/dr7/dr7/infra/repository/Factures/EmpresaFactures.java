package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;
import com.dr7.dr7.gateways.EntityRepository;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpresaFactures implements EntityRepository {
   private EmpresaRepository repository;
   UsuarioRepository usuarioRepository;
   public EmpresaFactures(EmpresaRepository repository, UsuarioRepository clienteRepository){
       this.repository = repository;
       this.usuarioRepository = clienteRepository;
   }
    public EmpresaFactures(){}

    @Override
    public EmpresaRespostaDTO registroEmpresarial(EmpresaDTO empresa) {
      var cliente = usuarioRepository.findOneById(empresa.clienteId(),empresa.cpf());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado na base de dados");
        }
      if(cliente!=null) {
          EmpresaEntity empresaEncontrada = repository.findOneByRazaoSocial(empresa.razaoSocial(),empresa.numeroDeTelefone(),empresa.email());
          if(empresaEncontrada!=null){
              if (empresaEncontrada.getRazaoSocial().contains(empresa.razaoSocial())
              ) {
                  throw new RuntimeException("Empresa ja existe");
              }else if(empresaEncontrada.getEmail().contains(empresa.email())|| empresaEncontrada.getNumeroDeTelefone().contains(empresa.numeroDeTelefone())){
                  throw new RuntimeException("Telefone ou Email ja cadastrado");
              }
          }
          Empresa empresaModel = new Empresa(empresa);
          var resposta = repository.save(new EmpresaEntity(empresaModel,cliente));
          return new EmpresaRespostaDTO(resposta);
      }
      throw new RuntimeException("Cliente não encontrado na base de dados");
    }
    @Override
    public List<EmpresaDTO> lista() {
        return  repository.findAll().stream().map(EmpresaDTO::new).toList();
    }
}
