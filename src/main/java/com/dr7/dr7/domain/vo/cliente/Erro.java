package com.dr7.dr7.domain.vo.cliente;

public  class Erro implements  msg{
    private String erro;

    public Erro(String erro) {
        this.erro = erro;
    }
    @Override
    public String setErro(String s) {
        return "";
    }

    public String getErro() {
        return erro;
    }
}
