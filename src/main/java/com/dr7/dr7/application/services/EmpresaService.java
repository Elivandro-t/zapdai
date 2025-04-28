package com.dr7.dr7.application.services;

import com.dr7.dr7.application.emailService.EmailSendService;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;
import com.dr7.dr7.domain.vo.Endereco;
import com.dr7.dr7.infra.repository.Factures.EmpresaFactures;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpresaService {
    private EmailSendService emailSendService;
    EmpresaFactures empresaFactures;
    public EmpresaService(EmpresaFactures factures,EmailSendService emailSendService){

        this.empresaFactures = factures;
        this.emailSendService = emailSendService;
    }
   public EmpresaRespostaDTO registroEmpresarial(EmpresaDTO empresa){
       var conta = empresaFactures.registroEmpresarial(empresa);
       if (conta!=null){
           emailSendService.emailSend(conta.email(),"Conta criada com sucesso!");
       }
      return conta;
    }

    public Map<String, List<EmpresaDTO>> all() {
        Map<String,List<EmpresaDTO>> dto = new HashMap<>();
       var lista = empresaFactures.lista();
       dto.put("empresas",lista);
       return dto;
    }

}
