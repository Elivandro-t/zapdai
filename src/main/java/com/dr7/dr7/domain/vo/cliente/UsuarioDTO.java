package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;

public record UsuarioDTO(long clientId, String nome, String phoneNumer) {

    public UsuarioDTO(UsuarioEntity resposavel) {
       this(resposavel.getClientId(),resposavel.getNome(),resposavel.getPhoneNumer());
    }
}
