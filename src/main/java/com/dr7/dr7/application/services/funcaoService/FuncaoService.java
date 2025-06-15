package com.dr7.dr7.application.services.funcaoService;

import com.dr7.dr7.domain.vo.FuncaoDTO;
import com.dr7.dr7.gateways.FuncaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FuncaoService {
    FuncaoRespository funcaoRespository;
    public FuncaoService(FuncaoRespository funcaoService){
        this.funcaoRespository = funcaoService;
    }

    public Map<String, List<FuncaoDTO>> findAll(){
        return funcaoRespository.findAll();
    }
}
