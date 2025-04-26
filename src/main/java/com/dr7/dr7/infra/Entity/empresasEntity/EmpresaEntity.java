package com.dr7.dr7.infra.Entity.empresasEntity;

import com.dr7.dr7.domain.Produtos;
import com.dr7.dr7.domain.vo.Endereco;
import com.dr7.dr7.infra.Entity.EnderecoEntity;
import com.dr7.dr7.infra.Entity.pedidos.PedidosEntity;
import com.dr7.dr7.infra.Entity.produtosEntity.ProdutosEntity;
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
    private boolean ativo;
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
}
