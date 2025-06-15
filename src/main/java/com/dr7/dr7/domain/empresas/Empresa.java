package com.dr7.dr7.domain.empresas;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.Endereco;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;

import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Empresa {
    private String id;
    private String nomeCompania;
    private String cpfCnpj;
    private boolean ativo;
    private LocalTime dataCriacao;
    private Status status;
    private String logoEmpresa;
    private long pedidosFinalizados;
    private String numeroDeTelefone;
    private List<Produto> produtos;
    private Endereco endereco;
    private String email;
    private Usuario resposavel;

    public Empresa(EmpresaDTO empresa) {
         validaCnpj(empresa.cpfCnpj());
        this.cpfCnpj = empresa.cpfCnpj();
        this.nomeCompania = empresa.nomeCompania();
        this.numeroDeTelefone = empresa.numeroDeTelefone();
        this.endereco = new Endereco(empresa.endereco());
        this.email = empresa.email();
    }
    public Empresa(){}

    public Empresa(EmpresaEntity empresaEntity) {
        this.id = empresaEntity.getIdEmpresa();
        this.cpfCnpj = empresaEntity.getCpfCnpj();
        this.nomeCompania = empresaEntity.getNomeCompania();
        this.numeroDeTelefone = empresaEntity.getNumeroDeTelefone();
        this.email = empresaEntity.getEmail();
    }


    public Usuario getResposavel() {
        return resposavel;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
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
             validaCPF(cnpj);
         }
    }
    private void validaCPF(String cnpj){
      String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cnpj);
        if(!matcher.matches()){
            throw new RuntimeException("Cpf ou Cnpj invalido");
        }

    }

    public String getId() {
        return id;
    }
}
