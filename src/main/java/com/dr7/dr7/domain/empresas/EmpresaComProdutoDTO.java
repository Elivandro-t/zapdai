package com.dr7.dr7.domain.empresas;

public class EmpresaComProdutoDTO {
    private String idEmpresa;
    private String nomeCompania;
    private String numeroDeTelefone;
    private String email;
    private Long idProduto;
    private String producName;
    private Float price;
    private String categoriaNome;

    public EmpresaComProdutoDTO(
            String idEmpresa,
            String nomeCompania,
            String numeroDeTelefone,
            String email,
            Long idProduto,
            String producName,
            Float price,
            String categoriaNome
    ) {
        this.idEmpresa = idEmpresa;
        this.nomeCompania = nomeCompania;
        this.numeroDeTelefone = numeroDeTelefone;
        this.email = email;
        this.idProduto = idProduto;
        this.producName = producName;
        this.price = price;
        this.categoriaNome = categoriaNome;
    }
    public EmpresaComProdutoDTO(){}

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductName() {
        return producName;
    }

    public void setProductName(String producName) {
        this.producName = producName;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDeTelefone() {
        return numeroDeTelefone;
    }

    public void setNumeroDeTelefone(String numeroDeTelefone) {
        this.numeroDeTelefone = numeroDeTelefone;
    }

    public String getNomeCompania() {
        return nomeCompania;
    }

    public void setNomeCompania(String nomeCompania) {
        this.nomeCompania = nomeCompania;
    }
}
