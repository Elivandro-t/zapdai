package com.dr7.dr7.infra.repository.Entity.cliente;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.EntregadorEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.PedidosEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;
    private String nome;
    private String phoneNumer;
    private String cpf;
    private String datanascimento;
    private LocalDateTime createdTime;
    private String sexo;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PerfilEntity> roles;
    @OneToMany(mappedBy = "resposavel",cascade = CascadeType.PERSIST)
    private List<EmpresaEntity> empresa;
    @OneToOne
    private EntregadorEntity entregador;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    private EnderecoEntity endereco;
    private String img;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<PedidosEntity> pedidos;
    public UsuarioEntity(Usuario usuarioModel){
            this.nome = usuarioModel.getNome();
            this.phoneNumer = usuarioModel.getPhoneNumer();
            this.datanascimento = usuarioModel.getDatanascimento();
            this.cpf = usuarioModel.getCpf();
            this.email = usuarioModel.getEmail();
            this.password = usuarioModel.getPassword();
            this.createdTime = usuarioModel.getCreatedTime();
            this.sexo = usuarioModel.getSexo();
        if (usuarioModel.getRole() != null) {
            this.roles = usuarioModel.getRole().stream().map(PerfilEntity::new).toList();
        } else {
            this.roles = new ArrayList<>(); // ou null, se preferir
        }
    }
    public UsuarioEntity(){}


    public long getClientId() {
        return clientId;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<PerfilEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<PerfilEntity> roles) {
        this.roles = roles;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorities = new ArrayList<>();
       authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
       for (PerfilEntity role:roles){
           if(role.getNome().equals("ROLE_ADMIN")){
               authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
           }
       }
       return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<EmpresaEntity> getEmpresa() {
        return empresa;
    }

    public EntregadorEntity getEntregador() {
        return entregador;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public List<PedidosEntity> getPedidos() {
        return pedidos;
    }

    public String getImg() {
        return img;
    }

}
