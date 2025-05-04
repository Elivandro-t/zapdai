package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.EnderecoDTO;

import java.util.List;

public record UsuarioResponseDTO(
        long clientId,
        String nome,
        String phoneNumer,
        String cpf,
        String dataNascimento,
        String sexo,
        String email,
        List<PerfilDTO> role
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getClientId(),
                usuario.getNome(),
                usuario.getPhoneNumer(),
                usuario.getCpf(),
                usuario.getDatanascimento(),
                usuario.getSexo(),
                usuario.getEmail(),
                usuario.getRole().stream().map(PerfilDTO::new).toList()
        );
    }
}
