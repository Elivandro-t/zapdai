package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.domain.empresas.EmpresaComProdutoDTO;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;
import com.dr7.dr7.domain.vo.RespostaEmpresa;
import com.dr7.dr7.gateways.EntityRepository;
import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.repository.PerfilRespository;
import com.dr7.dr7.infra.repository.repository.PlanosRespository;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpresaFactures implements EntityRepository {
   private EmpresaRepository repository;
   UsuarioRepository usuarioRepository;
   PerfilRespository perfilRespository;
   private PlanosRespository planosRespository;
   public EmpresaFactures(EmpresaRepository repository, UsuarioRepository clienteRepository,PlanosRespository planosRespository, PerfilRespository perfilRespository){
       this.repository = repository;
       this.usuarioRepository = clienteRepository;
       this.planosRespository = planosRespository;
       this.perfilRespository = perfilRespository;
   }
    public EmpresaFactures(){}

    @Override
    public EmpresaRespostaDTO registroEmpresarial(EmpresaDTO empresa) {
        List<PerfilEntity> roles = new ArrayList<>();
        var cliente = usuarioRepository.findOneById(empresa.usuario().id());
        var plano = planosRespository.findById(empresa.planoId()).orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        var perfilConvert = convertePerfil(plano.getTitle());
        var perfil = perfilRespository.findOnePerfilEntityByNameAndAtivoTrue(perfilConvert).orElseThrow(()->new RuntimeException("Sem perfil econtrado!"));
//        if (cliente == null) {
//            throw new RuntimeException("Cliente não encontrado na base de dados");
//        }


      if(cliente!=null) {
          EmpresaEntity empresaEncontrada = repository.findOneByRazaoSocial(empresa.cpfCnpj(),empresa.numeroDeTelefone(),empresa.email());
          if(empresaEncontrada!=null){
              if (empresaEncontrada.getCpfCnpj().contains(empresa.cpfCnpj())
              ) {
                  throw new RuntimeException("Empresa ja existe");
              }else if(empresaEncontrada.getEmail().contains(empresa.email())|| empresaEncontrada.getNumeroDeTelefone().contains(empresa.numeroDeTelefone())){
                  throw new RuntimeException("Telefone ou Email ja cadastrado");
              }
          }
          roles.add(perfil);
          cliente.setRoles(roles);

          Empresa empresaModel = new Empresa(empresa);
          var resposta = repository.save(new EmpresaEntity(empresaModel,cliente,plano));
          usuarioRepository.save(cliente);
          return new EmpresaRespostaDTO(resposta);
      }

      throw new RuntimeException("Cliente não encontrado na base de dados");
    }
    private String convertePerfil(String nome){
       String perfil = null;
       switch (nome){
           case "Zapdai Start":
               perfil = "ROLE_PLANO_START";
               break;
           case "Zapdai Plus":
               perfil = "ROLE_PLANO_PRO";
               break;
           case "Zapdai Premium":
               perfil = "ROLE_PLANO_PLUS";
               break;
           default:
               break;


       }
       return perfil;
    }
    @Override
    public List<EmpresaDTO> lista() {
        return  repository.findAll().stream().map(EmpresaDTO::new).toList();
    }
    @Override
    public List<RespostaEmpresa> find(){
      List<RespostaEmpresa>  lista = repository.findAllEmpresas().stream().map(RespostaEmpresa::from).toList();
      return  lista;
    }

    @Override
    public Page<EmpresaComProdutoDTO> findOndeProdutoCategoria(Long idCategoria, Pageable page) {
        Page<EmpresaComProdutoDTO> pageResult = repository.findAllEmpresasByCategoria(idCategoria, page);
        return new PageImpl<>(pageResult.stream().toList());// retorna a página completa, não só a lista
    }
}
