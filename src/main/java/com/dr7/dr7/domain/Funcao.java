package com.dr7.dr7.domain;

import com.dr7.dr7.domain.vo.FuncaoSubDTO;
import com.dr7.dr7.infra.repository.Entity.funcoes.FuncaoEntity;
import jakarta.persistence.PrePersist;

import java.util.List;
import java.util.Random;

public class Funcao {
    private String id;
    private String name;
    private String router;
    private String icone;
    private Boolean ativo;
   private Boolean menuItens = false;
    private Boolean iconeItem = false;
    private List<FuncaoSubDTO> children;

    public Funcao(FuncaoEntity e) {
        this.id = e.getId();
        this.name = e.getName();
        this.icone = e.getIcone();
        this.router = e.getRouter();
        this.ativo = e.getAtivo();
        if(e.getChildren()!=null){
            this.children = e.getItens().stream().map(s->new FuncaoSubDTO(s)).toList();

        }
    }
    public Funcao(){}

    @PrePersist
    public void gerarId() {
        if (id == null) {
            long timestamp = System.currentTimeMillis();
            int random = new Random().nextInt(99999);
            this.id = "fun-" + timestamp + "-" + random;
        }
    }
    public String getId() {
        return id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public String getIcone() {
        return icone;
    }

    public String getRouter() {
        return router;
    }

    public String getName() {
        return name;
    }

    public List<FuncaoSubDTO> getItens() {
        return children;
    }

    public Boolean getMenuItens() {
        return menuItens;
    }

    public Boolean getIconeItem() {
        return iconeItem;
    }
}
