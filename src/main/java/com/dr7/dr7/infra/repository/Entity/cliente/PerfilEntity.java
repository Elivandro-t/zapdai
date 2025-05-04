package com.dr7.dr7.infra.repository.Entity.cliente;

import com.dr7.dr7.domain.Auth.Perfil;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name="perfil")
public class PerfilEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perfilId;
    private String name;
    private boolean ativo;
    @ManyToMany
    List<UsuarioEntity> usuarioEntities;
    public PerfilEntity(Perfil perfil, UsuarioEntity user, Long perfilId) {
        this.ativo = perfil.isAtivo();

        this.name = perfil.getName();
        this.perfilId = perfilId;
    }
    public  PerfilEntity(){}
    public PerfilEntity(Perfil perfil) {
        this.name = perfil.getName();
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public String getNome() {
        return name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }


}
