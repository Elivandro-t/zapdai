package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.planos.Planos;
import com.dr7.dr7.domain.vo.planosDTO.PlanosDTO;
import com.dr7.dr7.gateways.PlanosRespositoryEntity;
import com.dr7.dr7.infra.repository.Entity.planos.PlanosEntity;
import com.dr7.dr7.infra.repository.repository.PlanosRespository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanosFacture implements PlanosRespositoryEntity {
    PlanosRespository respository;
    public PlanosFacture( PlanosRespository respository){
        this.respository = respository;

    }

    @Override
    public void save(PlanosDTO planoDTO) {
        Planos planos = new Planos(planoDTO);
        var Response = respository.findByTitulo(planoDTO.title());
                if(Response.isPresent()){
                    throw new RuntimeException("Planos ja criado");
                };
        PlanosEntity planosEntity = new PlanosEntity(planos);
    }

    @Override
    public List<PlanosDTO> findAll() {
        var response = respository.findAll().stream().map(Planos::new).toList();
        return response.stream().map(e->new PlanosDTO(e)).toList();
    }

    @Override
    public PlanosDTO findOne(String name) {
        var response = respository.findByTitulo(name);
        return new PlanosDTO(response.get());
    }
}
