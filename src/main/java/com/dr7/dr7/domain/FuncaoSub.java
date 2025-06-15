package com.dr7.dr7.domain;

import com.dr7.dr7.domain.vo.FuncaoSubDTO;

public class FuncaoSub {
   private String id;
    private String name;
    private String icone;
    private  String router;

    public FuncaoSub(FuncaoSub e) {
        this.id=e.id;
        this.name = e.name;
        this.icone = e.icone;
        this.router = e.router;
    }

    public String getId() {
        return id;
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
