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
    private String idEmpresa;
    private String nameEmpresa;
    private Float price;
    private Float peso;
    private boolean statusProdutos;
    private Long categoriaProdutos;
    private String descricao;
    private Integer quantidade;
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoriaProdutos categoria;
    @ManyToOne(fetch = FetchType.EAGER)
    private EmpresaEntity empresa;

    public ProdutosEntity(Produto produto,EmpresaEntity empresa) {
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
        this.empresa = empresa;
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

    public String getIdEmpresa() {
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


    public Long getCategoriaProdutos() {
        return categoriaProdutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }
    public CategoriaProdutos getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProdutos categoria) {
        this.categoria = categoria;
    }
}
