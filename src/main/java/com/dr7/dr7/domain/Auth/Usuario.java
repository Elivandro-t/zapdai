package com.dr7.dr7.domain.Auth;

import com.dr7.dr7.domain.vo.Endereco;
import com.dr7.dr7.domain.vo.cliente.PerfilDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.EntregadorEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.PedidosEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    private final String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

    private long clientId;
    private String nome;
    private String phoneNumer;
    private String cpf;
    private String datanascimento;
    private LocalDateTime createdTime;
    private String sexo;
    private String email;
    private String password;
    private List<EmpresaEntity> empresa;
    private EntregadorEntity entregador;
    private Endereco endereco;
    private String img;
    private boolean ativo;
    private String verifyCode;
    private List<PedidosEntity> pedidos;
    List<Perfil> role;
    public Usuario(UsuarioRegistoDTO dto){
           this.validaCpf(dto.cpf()) ;
           this.validaEmail(dto.email());
           this.nome = dto.nome();
           this.phoneNumer = dto.phoneNumer();
           this.datanascimento = dto.dataNascimento();
           this.cpf = dto.cpf();
           this.email = dto.email();
           this.password = dto.password();
           this.createdTime = LocalDateTime.now();
           this.sexo = dto.sexo();


        //Precisa ser criptografada, feito para teste no momento e precisa criar o spring security,
    }

    public Usuario(UsuarioEntity cliente) {
        this.clientId = cliente.getClientId();
        this.nome = cliente.getNome();
        this.phoneNumer = cliente.getPhoneNumer();
        this.datanascimento = cliente.getDatanascimento();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.password = cliente.getPassword();
        this.createdTime = LocalDateTime.now();
        this.sexo = cliente.getSexo();
        this.img = cliente.getImg();
       if(cliente.getRoles()!=null){
           this.role = cliente.getRoles().stream().map(Perfil::new).toList();
       }
       if(cliente.getEndereco()!=null) {
           this.endereco = new Endereco(cliente.getEndereco());
       }
    }

    private void validaCpf(String cpf){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cpf);
        if(!matcher.matches()){
            throw new RuntimeException("CPF invalido");
        }
    }
    private void validaEmail(String email){

        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        System.out.println("Meu email "+email);
        if(!matcher.matches()){
            throw new RuntimeException("Email invalido");
        }
    }

    public String getRegexEmail() {
        return regexEmail;
    }

    public String getRegex() {
        return regex;
    }

    public long getClientId() {
        return clientId;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImg() {
        return img;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public EntregadorEntity getEntregador() {
        return entregador;
    }

    public List<EmpresaEntity> getEmpresa() {
        return empresa;
    }

    public List<PedidosEntity> getPedidos() {
        return pedidos;
    }

    public List<Perfil> getRole() {
        return role;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
