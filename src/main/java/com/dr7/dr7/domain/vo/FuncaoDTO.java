package com.dr7.dr7.domain.vo;

import com.dr7.dr7.domain.Funcao;

import java.util.List;

public record FuncaoDTO(String id, String name, String router, String icone, Boolean ativo, List<FuncaoSubDTO> children) {
    public FuncaoDTO(Funcao e) {
        this(e.getId(),e.getName(),e.getRouter(),e.getIcone(),e.getAtivo(),e.getItens().stream().map(FuncaoSubDTO::new).toList());
    }
}
