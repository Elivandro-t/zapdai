package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.cliente.UsuarioDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import com.dr7.dr7.infra.config.JWTService;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.validation.Validators;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioFactures implements UsuarioIntefaceRepository {
    UsuarioRepository usuarioRepository;
    AuthenticationManager authenticationManager;
    JWTService service;
    Validators validators;
    public  UsuarioFactures(
            UsuarioRepository usuarioRepository
            ,AuthenticationManager authenticationManager,
            JWTService service,
            Validators validators
    ){
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.validators = validators;
    }
    @Override
    public UsuarioResponseDTO registroEmpresarial(UsuarioRegistoDTO usuario) {
       Optional usuarioEntity = usuarioRepository.findOneByEmailAndByCpf(usuario.email(),usuario.cpf());
       if(usuarioEntity.isPresent()){
           throw new RuntimeException("Usuario já existe");
       }
       validators.valid(usuario.password());
        Usuario usuarioModel = new Usuario(usuario);
       usuarioRepository.save(new UsuarioEntity(usuarioModel));
        return new UsuarioResponseDTO(usuarioModel);

    }

    @Override
    public List<UsuarioResponseDTO> lista() {
        return List.of();
    }

    @Override
    public UsuarioRespostaCpfDTO findByUsuarioByCpf(String cpf) {
        var usuario = usuarioRepository.findOneByCpf(cpf);
         var usuarioModel = new Usuario(usuario);
        return new UsuarioRespostaCpfDTO(usuarioModel);
    }

    @Override
    public Token sigin(AuthLoginDTO login) {
        var token =new UsernamePasswordAuthenticationToken(login.email(),login.password());
        var entity =  authenticationManager.authenticate(token);

        var usuario = entity.getPrincipal();

        String tokenname = service.GeraToken(new UsuarioDTO((UsuarioEntity) usuario),entity.getAuthorities());
        return new Token(tokenname);
    }
}
