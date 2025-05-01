package com.dr7.dr7.domain.produto;

import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.persistence.ManyToOne;

public class Produto {
    private long idProduto;
    private String imgProduct;
    private String nomeProduto;
    private long idEmpresa;
    private String nameEmpresa;
    private Float preco;
    private Float peso;
    private boolean statusProdutos;
    private Long categoriaProdutos;
    private String categoriaProdutosName;
    private EmpresaEntity pedidos;
    private String descricao;
    private Integer quantidade;
    public long getIdEmpresa() {
        return idEmpresa;
    }

    public String getNameEmpresa() {
        return nameEmpresa;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public EmpresaEntity getPedidos() {
        return pedidos;
    }

    public String getCategoriaProdutosName() {
        return categoriaProdutosName;
    }

    public Long getCategoriaProdutos() {
        return categoriaProdutos;
    }

    public boolean isStatusProdutos() {
        return statusProdutos;
    }

    public Float getPeso() {
        return peso;
    }

    public Float getPreco() {
        return preco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public Produto(ProdutoDTO produtodto) {
        this.imgProduct = produtodto.imgProduct();
        this.nomeProduto = produtodto.productName();
        this.idEmpresa = produtodto.idEmpresa();
        this.nameEmpresa = produtodto.nameEmpresa();
        this.preco = produtodto.price();
        this.peso = produtodto.peso();
        this.statusProdutos = true;
        this.categoriaProdutos = produtodto.categoriaProduct();
        this.descricao = produtodto.description();
        this.quantidade = produtodto.amountQTD();
        this.categoriaProdutosName = produtodto.categoriaProdutosName();

    }
    public Produto(ProdutosEntity produtodto) {
        this.imgProduct = produtodto.getImgProduct();
        this.nomeProduto = produtodto.getProducName();
        this.idEmpresa = produtodto.getIdEmpresa();
        this.nameEmpresa = produtodto.getNameEmpresa();
        this.preco = produtodto.getPrice();
        this.peso = produtodto.getPeso();
        this.statusProdutos = true;
        this.categoriaProdutos = produtodto.getCategoriaProdutos();
        this.descricao = produtodto.getDescricao();
        this.quantidade = produtodto.getQuantidade();
        this.categoriaProdutosName = produtodto.getCategoriaProdutosName();

    }
    public Produto(){}
}
