package com.dr7.dr7.webService.controlerEmpresa;

import com.dr7.dr7.application.services.EmpresaService;
import com.dr7.dr7.domain.empresas.EmpresaComProdutoDTO;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.RespostaEmpresa;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
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
    @GetMapping("lista")
    public ResponseEntity<List<RespostaEmpresa>> find(){
        return ResponseEntity.ok(service.find());
    }

    @GetMapping("categorias")
    public ResponseEntity<?>  listaPostCategorias(@RequestParam("id") Long id, @PageableDefault(page = 0, size = 100) Pageable page){
        var pages = service.buscaPorCategoria(id,page);
        return ResponseEntity.ok( pages);
    }
}
