package com.dr7.dr7.webService;

import com.dr7.dr7.application.services.PlanosServices.PlanosService;
import com.dr7.dr7.domain.vo.planosDTO.PlanosDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("zapdai/v1/planos-current")
public class PlanosControler {
     PlanosService planosService;
    public PlanosControler(PlanosService planosService){
   this.planosService = planosService;
    }
    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody @Valid PlanosDTO planos, UriComponentsBuilder builder){
        URI uri = builder.path("").build().toUri();
         planosService.save(planos);
       return ResponseEntity.created(uri).body(Map.of("ok","Criado com sucesso!"));

    }
    @GetMapping("/lista")
    public ResponseEntity<Map<String, List<PlanosDTO>>> findAll(){
       var lista = planosService.findAll();
        return  ResponseEntity.ok().body(lista);

    }
    @PostMapping("/one")
    public ResponseEntity<Map<String,PlanosDTO>> save(@RequestBody @Valid String name){
       var item = planosService.findOne(name);
        return ResponseEntity.ok().body(item);

    }
}
