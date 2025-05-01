package com.dr7.dr7.infra.repository.Entity.produtosEntity;

import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "Produtos")
public class ProdutosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduto;
    private String imgProduct;
    private String producName;
    private long idEmpresa;
    private String nameEmpresa;
    private Float price;
    private Float peso;
    private boolean statusProdutos;
    private Long categoriaProdutos;
    private String categoriaProdutosName;
    private String descricao;
    private Integer quantidade;

    public ProdutosEntity(Produto produto) {
        this.imgProduct = produto.getImgProduct();
        this.producName = produto.getNomeProduto();
        this.idEmpresa = produto.getIdEmpresa();
        this.nameEmpresa = produto.getNameEmpresa();
        this.price = produto.getPreco();
        this.peso = produto.getPeso();
        this.statusProdutos = produto.isStatusProdutos();
        this.categoriaProdutos = produto.getCategoriaProdutos();
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.categoriaProdutosName = produto.getCategoriaProdutosName();
    }
    public ProdutosEntity(){}

    public long getIdProduto() {
        return idProduto;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public String getProducName() {
        return producName;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public String getNameEmpresa() {
        return nameEmpresa;
    }
    public Float getPrice() {
        return price;
    }

    public Float getPeso() {
        return peso;
    }

    public boolean isStatusProdutos() {
        return statusProdutos;
    }

    public String getCategoriaProdutosName() {
        return categoriaProdutosName;
    }

    public Long getCategoriaProdutos() {
        return categoriaProdutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
