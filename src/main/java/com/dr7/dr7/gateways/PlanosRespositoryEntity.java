package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.vo.planosDTO.PlanosDTO;
import com.dr7.dr7.domain.vo.planosDTO.PlanosResponseDTO;

import java.util.List;

public interface PlanosRespositoryEntity {
    void save(PlanosDTO plano);
    List<PlanosResponseDTO> findAll();
    PlanosDTO findOne(String name);
}
