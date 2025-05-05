package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.Auth.Perfil;
import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.cliente.UsuarioDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import com.dr7.dr7.infra.config.JWTService;
import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.repository.PerfilRespository;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.validation.Validators;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioFactures implements UsuarioIntefaceRepository {
    UsuarioRepository usuarioRepository;
    AuthenticationManager authenticationManager;
    JWTService service;
    Validators validators;
    PerfilRespository perfilRespository;
    public  UsuarioFactures(
            UsuarioRepository usuarioRepository
            ,AuthenticationManager authenticationManager,
            JWTService service,
            Validators validators,
            PerfilRespository perfilRespository
    ){
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.validators = validators;
        this.perfilRespository = perfilRespository;
    }
    @Override
    public void registroEmpresarial(UsuarioRegistoDTO usuario) {
        PerfilEntity perfil = perfilRespository.findOnePerfilEntityByNameAndAtivoTrue("ROLE_USER");
        List<PerfilEntity> listaPerdil = new ArrayList<>();
       Optional usuarioEntity = usuarioRepository.findOneByEmailAndByCpfAndByFone(usuario.email(),usuario.cpf(),usuario.phoneNumer());
       if(usuarioEntity.isPresent()){
           throw new RuntimeException("Usuario já existe");
       }
       validators.valid(usuario.password());

        Usuario usuarioModel = new Usuario(usuario);
        var usuraioEntity = new UsuarioEntity(usuarioModel);
        if(perfil!=null){
            listaPerdil.add(perfil);
        }
        usuraioEntity.setRoles(listaPerdil);
       usuarioRepository.save(usuraioEntity);

    }

    @Override
    public List<UsuarioResponseDTO> lista() {
        return List.of();
    }

    @Override
    public UsuarioRespostaCpfDTO findByUsuarioByCpf(String cpf) {
        var usuario = usuarioRepository.findOneByCpf(cpf);
        if(usuario!=null){
            var usuarioModel = new Usuario(usuario);
            return new UsuarioRespostaCpfDTO(usuarioModel);
        }
        throw new RuntimeException("Usuario não encontrado na base de dados!git ");
    }

    @Override
    public Token sigin(AuthLoginDTO login) {
        var token =new UsernamePasswordAuthenticationToken(login.email(),login.password());
        var entity =  authenticationManager.authenticate(token);

        var usuario = entity.getPrincipal();

        String tokenname = service.GeraToken(new Usuario((UsuarioEntity) usuario),entity.getAuthorities());
        return new Token(tokenname);
    }
}
