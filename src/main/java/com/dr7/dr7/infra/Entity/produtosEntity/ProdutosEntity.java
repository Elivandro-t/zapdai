package com.dr7.dr7.infra.Entity.produtosEntity;

import com.dr7.dr7.infra.Entity.empresasEntity.EmpresaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "Produtos")
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduto;
    private String imgProduct;
    private String nomeProduto;
    private long fornecedoId;
    private String NomeFornecedo;
    private Float preco;
    private Float peso;
    private boolean statusProdutos;
    @ManyToOne
    private CategoriaProdutos categoriaProdutos;
    @ManyToOne
    private EmpresaEntity pedidos;
    private String descricao;
    private Integer quantidade;

}
