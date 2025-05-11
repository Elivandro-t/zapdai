package com.dr7.dr7.domain.vo.planosDTO;

import com.dr7.dr7.domain.planos.Planos;
import com.dr7.dr7.infra.repository.Entity.planos.PlanosEntity;

public record PlanosDTO(String title, Double price, String description,String promocao,String subDescricaoPermition) {
    public PlanosDTO(Planos e) {
        this(e.getTitle(),e.getPrice(),e.getDescription(),e.getPromocao(),e.getSubDescricaoPermition());
    }

    public PlanosDTO(PlanosEntity planosEntity) {
        this(planosEntity.getTitle(),planosEntity.getPrice(),planosEntity.getDescription(),planosEntity.getPromocao(),planosEntity.getSubDescricaoPermition());
    }
}
