package com.dr7.dr7.webService.controlerEmpresa;

import com.dr7.dr7.application.services.ProdutosService;
import com.dr7.dr7.domain.vo.produtodto.BuscaProdutosDto;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("produtos")
public class ControlerProdutos {
    ProdutosService produtosService;
    public ControlerProdutos(ProdutosService produtosService){
        this.produtosService = produtosService;
    }
    @PostMapping("/registro")
    @Transactional
    public ResponseEntity<String> registroDeProduto(@RequestBody @Valid ProdutoDTO produtos, UriComponentsBuilder builder){
        URI uri = builder.path("/lista").build().toUri();
        produtosService.registroDeProdutos(produtos);
        return ResponseEntity.created(uri).body(Map.of("ok","Criado com sucesso").toString());
    }
    @GetMapping("/lista")
    public ResponseEntity findAll(){
        var lista = produtosService.findAll();
        return ResponseEntity.ok().body(lista);
    }
    @PostMapping("fornecedor")
    public ResponseEntity findOneProdutos(@RequestBody @Valid BuscaProdutosDto buscaProdutosDto){
        var lista = produtosService.findAllByProductByFornecedor(buscaProdutosDto);
        return ResponseEntity.ok().body(lista);
    }
}
