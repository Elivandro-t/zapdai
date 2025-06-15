package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.domain.vo.cliente.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UsuarioIntefaceRepository {
     void registroEmpresarial(UsuarioRegistoDTO usuario);
     List<UsuarioResponseDTO> lista();
     UsuarioRespostaCpfDTO findByUsuarioByCpf(String email);
     Token sigin(AuthLoginDTO login);
     Mensagem codigoDeVerificacao(EnviaCode code);
     msg VerificaCode(VerificaCode code);
     Mensagem alterPassows(NewPassword pwd);
     void avatar(Long idUsuario, MultipartFile file) throws IOException;
     void atualizaDadosEndereco(EnderecoDTO e, Long idUsuario,String nome,String sexo,String numero);
}
