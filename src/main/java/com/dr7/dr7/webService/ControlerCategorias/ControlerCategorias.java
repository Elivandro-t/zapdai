package com.dr7.dr7.webService.ControlerCategorias;

import com.dr7.dr7.application.services.CategoriaService;
import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.infra.config.Convertejson;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("categorias")
public class ControlerCategorias {
    @Autowired
   private Convertejson convertejson;
    private final CategoriaService categoriaService;
    public  ControlerCategorias(CategoriaService service){
        categoriaService = service;
    }
    @PostMapping("cadastro")
    @Transactional
    public ResponseEntity<Map> save(@RequestParam("data") String data, @RequestParam("file") MultipartFile file, UriComponentsBuilder builder) throws IOException {
        CategoriaDTO categoriaDTO = convertejson.converter(data,CategoriaDTO.class);
        URI uri = builder.path("categoria").build().toUri();
        categoriaService.saveCategoria(categoriaDTO,file);
        return ResponseEntity.created(uri).body(Map.of("ok","Adicionado com sucesso!"));
    }
    @GetMapping("lista")
    public ResponseEntity<Map<String,List<CategoriaDTO>>> lista(){
      var lista = categoriaService.lista();
      return ResponseEntity.ok(lista);
    }
    @GetMapping("imagens/{name}")
    public ResponseEntity<Resource> icone(@PathVariable("name") String name){
        var res = categoriaService.ListaImagensId(name);
        return res;
    }
}
