package com.dr7.dr7.domain.vo.planosDTO;

import com.dr7.dr7.domain.planos.Planos;
import com.dr7.dr7.infra.repository.Entity.planos.PlanosEntity;

public record PlanosResponseDTO(String planoId,String title, Double price, String description, String promocao, String subDescricaoPermition) {
    public PlanosResponseDTO(Planos e) {
        this(e.getId(),e.getTitle(),e.getPrice(),e.getDescription(),e.getPromocao(),e.getSubDescricaoPermition());
    }
}
