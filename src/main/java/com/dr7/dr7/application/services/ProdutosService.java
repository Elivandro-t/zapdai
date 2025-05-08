package com.dr7.dr7.application.services;

import com.dr7.dr7.domain.vo.produtodto.BuscaProdutosDto;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import com.dr7.dr7.gateways.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutosService {
    @Autowired
   private ProdutoRepository produtoRepository;
//    Princípio de Inversão de Dependência (Dependency Inversion Principle - DIP)
    //Chamado a inteface ProdutosRepositoty e mandando para o ProdutosFacture usando princio
    public void registroDeProdutos(ProdutoDTO produto){
          produtoRepository.saveProduct(produto);
    }
    public Map<String, List<ProdutoResponseDTO>> findAll(){
        Map<String,List<ProdutoResponseDTO>> listMap = new HashMap<>();
        var lista =  produtoRepository.findAll();
        listMap.put("products",lista);
        return listMap;
    }
    public  Map<String,List<ProdutoResponseDTO>> findAllByProductByFornecedor(BuscaProdutosDto busca) {
        Map<String,List<ProdutoResponseDTO>> listMap = new HashMap<>();
       var lista = produtoRepository.findOneProdutoFornecedo(busca.nameEmpresa(),busca.idEmpresa());
       listMap.put("produtos",lista);

        return listMap;
    }
}
