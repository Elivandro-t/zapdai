package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.domain.Auth.Perfil;
import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;

public record PerfilDTO(String name){
    public PerfilDTO(Perfil e) {
        this(e.getName());
    }
};

