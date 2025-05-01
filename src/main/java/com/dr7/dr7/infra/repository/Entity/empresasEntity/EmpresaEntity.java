package com.dr7.dr7.infra.repository.Entity.empresasEntity;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Empresas")
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmpresa;
    private String nomeCompania;
    private String razaoSocial;
    private boolean ativo = false;
    private LocalTime dataCriacao;
    private Status status;
    private String acessTokenMercadoPago;
    private String logoEmpresa;
    private long pedidosFinalizados;
    private String numeroDeTelefone;
    @OneToMany
    private List<ProdutosEntity> produtos;
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;
    private String email;
    private long pedidos;
    @ManyToOne( cascade = CascadeType.ALL)
    private UsuarioEntity resposavel;

    public EmpresaEntity(Empresa empresa,UsuarioEntity e){
        this.nomeCompania = empresa.getNomeCompania();
        this.razaoSocial = empresa.getRazaoSocial();
        this.dataCriacao = LocalTime.now();
        this.status = Status.retornaStatus("aprovacao");
        this.nomeCompania = empresa.getNomeCompania();
        this.numeroDeTelefone = empresa.getNumeroDeTelefone();
        this.endereco = new EnderecoEntity(empresa.getEndereco());
        this.email = empresa.getEmail();
        this.resposavel = e;
    }

    public UsuarioEntity getResposavel() {
        return resposavel;
    }

    public EmpresaEntity(){}
    public long getIdEmpresa() {
        return idEmpresa;
    }

    public String getNomeCompania() {
        return nomeCompania;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalTime getDataCriacao() {
        return dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public String getAcessTokenMercadoPago() {
        return acessTokenMercadoPago;
    }

    public String getLogoEmpresa() {
        return logoEmpresa;
    }

    public long getPedidosFinalizados() {
        return pedidosFinalizados;
    }

    public String getNumeroDeTelefone() {
        return numeroDeTelefone;
    }

    public List<ProdutosEntity> getProdutos() {
        return produtos;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public long getPedidos() {
        return pedidos;
    }

    public String getEmail() {
        return email;
    }
}
