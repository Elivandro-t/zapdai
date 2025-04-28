package com.dr7.dr7.web.controlerEmpresa;

import com.dr7.dr7.application.emailService.EmailSendService;
import com.dr7.dr7.application.services.EmpresaService;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("zapdai/v1/empresas")
public class EmpresaControler {
    private EmpresaService service;
    public EmpresaControler(EmpresaService service){
        this.service = service;
    }
    @PostMapping("registro")
    @Transactional
    public ResponseEntity<Retorn> registroDeEmpresas(@RequestBody @Valid EmpresaDTO empresaDTO, UriComponentsBuilder builder) throws MessagingException {
        URI uri = builder.path("/empresas/registro").build().toUri();
       var response = service.registroEmpresarial(empresaDTO);
        return ResponseEntity.created(uri).body(new Retorn("Criado Com sucesso",true,response.status()));
    }
    @GetMapping
    public ResponseEntity<Map<String,List<EmpresaDTO>>> lista(){
        return ResponseEntity.ok(service.all());
    }
}
