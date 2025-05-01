package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.domain.Auth.Usuario;

public record UsuarioRespostaCpfDTO(
        long clientId,
        String nome,
        String phoneNumer,
        String cpf,
        String dataNascimento,
        String sexo,
        String email) {
    public UsuarioRespostaCpfDTO(Usuario usuario) {
        this(usuario.getClientId(),
                usuario.getNome(),
                usuario.getPhoneNumer(),
                usuario.getCpf(),
                usuario.getDatanascimento(),
                usuario.getSexo(),
                usuario.getEmail());
    }
}
