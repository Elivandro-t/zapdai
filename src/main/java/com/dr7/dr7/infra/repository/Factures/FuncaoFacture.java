package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.Funcao;
import com.dr7.dr7.domain.vo.FuncaoDTO;
import com.dr7.dr7.gateways.FuncaoRespository;
import com.dr7.dr7.infra.repository.repository.FuncaesRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FuncaoFacture implements FuncaoRespository {
    FuncaesRepository funcaesRepository;
    public  FuncaoFacture(FuncaesRepository funcaesRepository){
        this.funcaesRepository = funcaesRepository;
    }
    @Override
    public Map<String, List<FuncaoDTO>> findAll() {
        Map<String,List<FuncaoDTO>> listMap = new HashMap<>();
       var lista = funcaesRepository.findAllByAtivoTrue().stream().map(Funcao::new).toList();
       var listadto = lista.stream().sorted(Comparator.comparingInt(f -> Integer.parseInt(f.getId().replace("funcao-", "")))).map(e->new FuncaoDTO(e)).toList();
       listMap.put("funcoes",listadto);
        return listMap;
    }
}
