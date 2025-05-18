package com.dr7.dr7.application.services.PlanosServices;

import com.dr7.dr7.domain.vo.planosDTO.PlanosDTO;
import com.dr7.dr7.domain.vo.planosDTO.PlanosResponseDTO;
import com.dr7.dr7.gateways.PlanosRespositoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanosService {
    private static final Logger log = LoggerFactory.getLogger(PlanosService.class);
    PlanosRespositoryEntity respository;
    public PlanosService(PlanosRespositoryEntity respository){
        this.respository = respository;

    }
    public void save(PlanosDTO plano) {
        respository.save(plano);

    }

    public Map<String,List<PlanosResponseDTO>> findAll() {
        Map<String,List<PlanosResponseDTO>> lista = new HashMap<>();
        var response = respository.findAll();
        lista.put("planos",response);
        return lista;

    }

    public Map<String,PlanosDTO> findOne(String name) {
        Map<String,PlanosDTO> itemMap = new HashMap<>();
        var item = respository.findOne(name);
        itemMap.put("plano",item);
        return itemMap;
    }
}
