package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.EnderecoDTO;

import java.util.List;

public record UsuarioRespostaCpfDTO(
        long clientId,
        String nome,
        String phoneNumer,
        String cpf,
        String dataNascimento,
        String sexo,
        String email,
        String avatar,
        List<PerfilDTO> role,
        EnderecoDTO endereco
) {
    public UsuarioRespostaCpfDTO(Usuario usuario) {
        this(usuario.getClientId(),
                usuario.getNome(),
                usuario.getPhoneNumer(),
                usuario.getCpf(),
                usuario.getDatanascimento(),
                usuario.getSexo(),
                usuario.getEmail(),
                usuario.getImg(),
                usuario.getRole().stream().map(PerfilDTO::new).toList(),
                new EnderecoDTO(usuario.getEndereco())

        );
    }
}
