package com.dr7.dr7.application.services;

import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    UsuarioIntefaceRepository usuarioIntefaceRepository;
    public UsuarioService(UsuarioIntefaceRepository usuarioIntefaceRepository){
        this.usuarioIntefaceRepository = usuarioIntefaceRepository;
    }

    public UsuarioResponseDTO save(UsuarioRegistoDTO dto){
        return usuarioIntefaceRepository.registroEmpresarial(dto);
    }
    public UsuarioRespostaCpfDTO findOneByCpf(String cpf){
        return usuarioIntefaceRepository.findByUsuarioByCpf(cpf);
    }
}
