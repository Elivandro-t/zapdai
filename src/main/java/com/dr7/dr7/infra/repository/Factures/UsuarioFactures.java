package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.cliente.Usuario;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioFactures implements UsuarioIntefaceRepository {
    UsuarioRepository usuarioRepository;
    public  UsuarioFactures( UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UsuarioResponseDTO registroEmpresarial(UsuarioRegistoDTO usuario) {
       Optional usuarioEntity = usuarioRepository.findOneByEmailAndByCpf(usuario.email(),usuario.cpf());
       if(usuarioEntity.isPresent()){
           throw new RuntimeException("Usuario já existe");
       }
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
}
