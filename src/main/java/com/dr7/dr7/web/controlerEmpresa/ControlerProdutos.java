package com.dr7.dr7.web.controlerEmpresa;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/zapdai/v1/produtos")
public class ControlerProdutos {
    ProdutosService produtosService;
    public ControlerProdutos(ProdutosService produtosService){
        this.produtosService = produtosService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<String> registroDeProduto(@RequestBody @Valid ProdutoDTO produtos, UriComponentsBuilder builder){
        URI uri = builder.path("/produtos").build().toUri();
        produtosService.registroDeProdutos(produtos);
        return ResponseEntity.created(uri).body("Criado com sucesso");
    }
    @GetMapping("lista")
    public CompletableFuture<Map<String,List<ProdutoResponseDTO>>> findAll(){
      return produtosService.findAll();
    }
    @PostMapping("fornecedor")
    public CompletableFuture<List<ProdutoResponseDTO>> findOneProdutos(@RequestBody @Valid BuscaProdutosDto buscaProdutosDto){
        return produtosService.findAllByProductByFornecedor(buscaProdutosDto);
    }
}
