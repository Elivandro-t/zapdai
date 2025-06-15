package com.dr7.dr7.infra.repository.Entity.cliente;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.EntregadorEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.PedidosEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean ativoCode = false;
    private  Integer exp = 0;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PerfilEntity> roles;
    @OneToMany(mappedBy = "resposavel",cascade = CascadeType.PERSIST)
    private List<EmpresaEntity> empresa;
    @OneToOne
    private EntregadorEntity entregador;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean ativo;
    private String verifyCode;
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
            this.password = criptofrafiaDeSenha(usuarioModel.getPassword());
            this.createdTime = usuarioModel.getCreatedTime();
            this.sexo = usuarioModel.getSexo();
            this.ativo = false;
            this.exp = 0;
            this.ativoCode = false;
        if (usuarioModel.getRole() != null) {
            this.roles = usuarioModel.getRole().stream().map(PerfilEntity::new).toList();
        } else {
            this.roles = new ArrayList<>(); // ou null, se preferir
        }
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public UsuarioEntity(){}
    public void atualizaEndereco(EnderecoDTO e,String nome,String sexo,String numero){
        this.nome = nome;
        this.sexo = sexo;
        this.phoneNumer = numero;
       if (this.endereco!=null){
           this.endereco.atualiza(e);
       }else{
           this.endereco = new EnderecoEntity(e);
       }
    }


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
    public String criptofrafiaDeSenha(String password){
        return this.password = new BCryptPasswordEncoder().encode(password);
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
        return !this.ativo;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void Verification(){
        this.exp++;
    }

    public int getExp() {
        return exp;
    }

    public void resetVerificationAttempts() {
        this.exp = 0;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Boolean getAtivoCode() {
        return ativoCode;
    }

    public void setAtivoCode(Boolean ativoCode) {
        this.ativoCode = ativoCode;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setImg(String img) {
        this.img = img;
    }



}
