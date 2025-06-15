package com.dr7.dr7.infra.repository.Entity.funcoes;

import com.dr7.dr7.domain.Funcao;
import com.dr7.dr7.domain.vo.FuncaoSubDTO;
import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "subfuncao")
public class FuncaoSub {
    @Id
    private String id;
    private String name;
    private String icone;
    private String router;
    @ManyToOne(fetch = FetchType.LAZY)
    private FuncaoEntity funcao;

    public FuncaoSub(Funcao e) {
        this.id = e.getId();
        this.name = e.getName();
        this.icone = e.getIcone();
        this.router = e.getRouter();
    }
    public FuncaoSub(){}

    public FuncaoSub(FuncaoSubDTO funcaoSubDTO) {
        this.id = funcaoSubDTO.id();
        this.name = funcaoSubDTO.name();
        this.icone = funcaoSubDTO.icone();
        this.router = funcaoSubDTO.router();
    }

    @PrePersist
    public void gerarId() {
        if (id == null) {
            long timestamp = System.currentTimeMillis();
            int random = new Random().nextInt(99999);
            this.id = "zapdai_fu-" + timestamp + "-" + random;
        }

    }

    public String getId() {
        return id;
    }

    public FuncaoEntity getFuncao() {
        return funcao;
    }

    public String getRouter() {
        return router;
    }

    public String getIcone() {
        return icone;
    }

    public String getName() {
        return name;
    }
}
