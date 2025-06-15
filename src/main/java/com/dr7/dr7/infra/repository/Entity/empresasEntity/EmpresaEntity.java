package com.dr7.dr7.infra.repository.Entity.empresasEntity;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.Entity.planos.PlanosEntity;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "Empresas")
public class EmpresaEntity {
    @Id
    private String idEmpresa;
    private String nomeCompania;
    private String cpfCnpj;
    private boolean ativo = false;
    private LocalTime dataCriacao;
    private Status status;
    private String logoEmpresa;
    private long pedidosFinalizados;
    private String numeroDeTelefone;
    @OneToMany(mappedBy = "empresa",cascade = CascadeType.PERSIST)
    private List<ProdutosEntity> produtos;
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;
    private String email;
    private long pedidos;
    @ManyToOne( cascade = CascadeType.ALL)
    private UsuarioEntity resposavel;
    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanosEntity plano; // Plano da empresa

    private boolean pagamentoRealizado; // Se o pagamento foi realizado ou não
    private LocalDate dataVencimento; // Data de vencimento do pagamento
    private LocalDate dataCriacaoEmpresa; // Data de criação da empresa no sistema
    private String statusPlano;

    public EmpresaEntity(Empresa empresa,UsuarioEntity e,PlanosEntity plano){
        this.nomeCompania = empresa.getNomeCompania();
        this.cpfCnpj = empresa.getCpfCnpj();
        this.dataCriacao = LocalTime.now();
        this.status = Status.retornaStatus("aprovacao");
        this.nomeCompania = empresa.getNomeCompania();
        this.numeroDeTelefone = empresa.getNumeroDeTelefone();
        this.endereco = new EnderecoEntity(empresa.getEndereco());
        this.email = empresa.getEmail();
        this.resposavel = e;
        this.plano = plano;
    }
    @PrePersist
    public void gerarId() {
        if (idEmpresa == null) {
            long timestamp = System.currentTimeMillis();
            int random = new Random().nextInt(99999);
            this.idEmpresa = "empresa-key" + timestamp + "-" + random;
        }


    }

    public UsuarioEntity getResposavel() {
        return resposavel;
    }

    public EmpresaEntity(){}
    public String getIdEmpresa() {
        return idEmpresa;
    }

    public String getNomeCompania() {
        return nomeCompania;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
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

    public void setProdutos(List<ProdutosEntity> produtos) {
        this.produtos = produtos;
    }



}
