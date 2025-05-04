package com.dr7.dr7.domain.Auth;

import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
public class Perfil {
    private Long perfilId;
    private String name;
    private boolean ativo;
    private Usuario user;
    public Perfil(String name) {
        this.ativo = true;
        this.name = name;
    }

    public Perfil(PerfilEntity e) {
        this.name = e.getNome();
    }

    public Usuario getUser() {
        return user;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getName() {
        return name;
    }
}
