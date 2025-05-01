package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.EnderecoDTO;

public record UsuarioResponseDTO(
        long clientId,
        String nome,
        String phoneNumer,
        String cpf,
        String dataNascimento,
        String sexo,
        String email,
        EnderecoDTO endereco
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getClientId(),
                usuario.getNome(),
                usuario.getPhoneNumer(),
                usuario.getCpf(),
                usuario.getDatanascimento(),
                usuario.getSexo(),
                usuario.getEmail(),
                new EnderecoDTO(usuario.getEndereco())
        );
    }
}
