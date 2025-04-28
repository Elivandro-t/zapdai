package com.dr7.dr7.infra.repository.Entity.empresasEntity;

public enum Status {
    RECUSADA("recusado"),
    AGUARDANDO_APROVACAO("aprovacao"),
    APROVADA("aprovada");

    private final String nome;

    Status(String data){
        this.nome = data;
    }
    public String getNome() {
        return nome;
    }
    public static Status retornaStatus(String nomeStatus){
        for (Status s:Status.values()){
            if(s.nome.equalsIgnoreCase(nomeStatus)){
                return s;
            }
        }
        return null;
    };
}
