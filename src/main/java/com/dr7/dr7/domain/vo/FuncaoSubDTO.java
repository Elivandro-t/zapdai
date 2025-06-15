package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.Funcao;
import com.dr7.dr7.infra.repository.Entity.funcoes.FuncaoSub;

public record FuncaoSubDTO (String id, String name, String icone, String router){

    public FuncaoSubDTO(FuncaoSub s) {
        this(s.getId(),s.getName(),s.getIcone(),s.getRouter());

    }

    public FuncaoSubDTO(FuncaoSubDTO s) {
        this(s.id(),s.name(),s.icone(),s.router());

    }
}
