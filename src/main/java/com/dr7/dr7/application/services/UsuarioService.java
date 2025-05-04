package com.dr7.dr7.application.services;

import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    UsuarioIntefaceRepository usuarioIntefaceRepository;
    public UsuarioService(UsuarioIntefaceRepository usuarioIntefaceRepository){
        this.usuarioIntefaceRepository = usuarioIntefaceRepository;

    }

    public void save(UsuarioRegistoDTO dto){

         usuarioIntefaceRepository.registroEmpresarial(dto);
    }
    public UsuarioRespostaCpfDTO findOneByCpf(String cpf){
        return usuarioIntefaceRepository.findByUsuarioByCpf(cpf);
    }

    public Token signin(AuthLoginDTO auth) {
       return usuarioIntefaceRepository.sigin(auth);

    }
}
