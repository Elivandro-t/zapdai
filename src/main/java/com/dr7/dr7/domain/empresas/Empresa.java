package com.dr7.dr7.domain.empresas;

import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.Endereco;

import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Empresa {
    private String nomeCompania;
    private String razaoSocial;
    private boolean ativo;
    private LocalTime dataCriacao;
    private Status status;
    private String acessTokenMercadoPago;
    private String logoEmpresa;
    private long pedidosFinalizados;
    private String numeroDeTelefone;
    private List<Produto> produtos;
    private Endereco endereco;
    private String email;

    public Empresa(EmpresaDTO empresa) {
        validaCnpj(empresa.razaoSocial());
        this.razaoSocial = empresa.razaoSocial();
        this.nomeCompania = empresa.nomeCompania();
        this.numeroDeTelefone = empresa.numeroDeTelefone();
        this.endereco = new Endereco(empresa.endereco());
        this.email = empresa.email();
    }
    public Empresa(){}


    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeCompania() {
        return nomeCompania;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    private void validaCnpj(String cnpj){
       String regex = "^(\\d{2})\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$";;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cnpj);
        if(!matcher.matches()){
            throw new RuntimeException("Cnpj invalido");
        }
    }
}
