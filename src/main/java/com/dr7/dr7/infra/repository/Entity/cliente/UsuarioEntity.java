package com.dr7.dr7.infra.repository.Entity.cliente;

import com.dr7.dr7.domain.cliente.Usuario;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.EntregadorEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.PedidosEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
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
    @OneToMany(mappedBy = "resposavel",cascade = CascadeType.PERSIST)
    private List<EmpresaEntity> empresa;
    @OneToOne
    private EntregadorEntity entregador;
    @OneToOne(cascade = CascadeType.ALL)
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
            this.endereco = new EnderecoEntity(usuarioModel.getEndereco());
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

    public String getPassword() {
        return password;
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
