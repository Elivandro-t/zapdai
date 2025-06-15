package com.dr7.dr7.webService.controlerEmpresa;

import com.dr7.dr7.application.services.ProdutosService;
import com.dr7.dr7.domain.vo.produtodto.BuscaProdutosDto;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import com.dr7.dr7.infra.config.Convertejson;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
@RequestMapping("/zapdai/v1/produtos")
public class ControlerProdutos {
    @Autowired
    Convertejson convertejson;
    ProdutosService produtosService;
    public ControlerProdutos(ProdutosService produtosService){
        this.produtosService = produtosService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<String> registroDeProduto(@RequestParam("data") @Valid String data, @RequestParam("file") MultipartFile file, UriComponentsBuilder builder) throws IOException {
        URI uri = builder.path("/produtos").build().toUri();
        ProdutoDTO produtos = convertejson.converter(data,ProdutoDTO.class);
        produtosService.registroDeProdutos(produtos,file);
        return ResponseEntity.created(uri).body("Criado com sucesso");
    }
    @GetMapping("lista")
    public ResponseEntity<Map<String,List<ProdutoResponseDTO>>> findAll(){
      var lista = produtosService.findAll();
     return ResponseEntity.ok().body(lista);
    }
    @PostMapping("/fornecedor")
    public ResponseEntity<Map<String,List<ProdutoResponseDTO>>> findOneProdutos(@RequestBody @Valid BuscaProdutosDto buscaProdutosDto){
        var lista  = produtosService.findAllByProductByFornecedor(buscaProdutosDto);
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("/avatar/{imagem}")
    public ResponseEntity<Resource> exibeImagem(@PathVariable String imagem){
        return produtosService.ExibeImagem(imagem);
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<ProdutoResponseDTO> findOnde(@PathVariable Long id){
        return ResponseEntity.ok(produtosService.findOne(id));
    }

}
