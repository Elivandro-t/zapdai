package com.dr7.dr7.infra.repository.Entity.funcoes;

import com.dr7.dr7.domain.Funcao;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "funcao")
public class FuncaoEntity {
    @Id
    private String id;
    private String name;
    private String router;
    private String icone;
    private Boolean ativo;
    private Boolean  menu_itens;
    private Boolean icone_item;
    @OneToMany(mappedBy = "funcao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FuncaoSub> children;
    public  FuncaoEntity(){}

    public FuncaoEntity(Funcao funcao) {
        this.id = funcao.getId();
        this.ativo = funcao.getAtivo();
        this.icone = funcao.getIcone();
        this.router = funcao.getRouter();
        this.name = funcao.getName();
        this.menu_itens = funcao.getMenuItens();
        this.icone_item = funcao.getIconeItem();
        this.children = funcao.getItens().stream().map(FuncaoSub::new).toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FuncaoSub> getItens() {
        return children;
    }

    public Boolean getMenuItens() {
        return menu_itens;
    }

    public List<FuncaoSub> getChildren() {
        return children;
    }

    public Boolean getIconeItem() {
        return icone_item;
    }
}
