package com.dr7.dr7.domain.empresas;

import com.dr7.dr7.domain.Produtos;
import com.dr7.dr7.domain.vo.Endereco;

import java.time.LocalTime;
import java.util.List;

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
    private List<Produtos> produtos;
    private Endereco endereco;
}
