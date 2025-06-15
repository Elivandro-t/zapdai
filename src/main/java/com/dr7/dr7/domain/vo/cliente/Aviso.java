package com.dr7.dr7.domain.vo.cliente;

public  class Aviso implements  msg{
   private String msg;


    @Override
    public String setErro(String b) {
        return "";
    }

    public Aviso(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
