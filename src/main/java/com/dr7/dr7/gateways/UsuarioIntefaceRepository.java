package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;

import java.util.List;

public interface UsuarioIntefaceRepository {
     UsuarioResponseDTO registroEmpresarial(UsuarioRegistoDTO usuario);
     List<UsuarioResponseDTO> lista();
     UsuarioRespostaCpfDTO findByUsuarioByCpf(String cpf);
     Token sigin(AuthLoginDTO login);
}
