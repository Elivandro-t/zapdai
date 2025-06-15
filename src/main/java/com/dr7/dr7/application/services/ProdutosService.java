package com.dr7.dr7.application.services;

import com.dr7.dr7.domain.vo.produtodto.BuscaProdutosDto;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import com.dr7.dr7.gateways.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutosService {
    @Autowired
   private ProdutoRepository produtoRepository;
//    Princípio de Inversão de Dependência (Dependency Inversion Principle - DIP)
    //Chamado a inteface ProdutosRepositoty e mandando para o ProdutosFacture usando princio
    public void registroDeProdutos(ProdutoDTO produto, MultipartFile file) throws IOException {

        produtoRepository.saveProduct(produto,file);
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
    public ResponseEntity<Resource> ExibeImagem(String imagem){
        Path path = Paths.get("produtos").resolve(imagem);
        try{
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists()||resource.isReadable()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }
            return  ResponseEntity.notFound().build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }
   public ProdutoResponseDTO findOne(Long id){
       return produtoRepository.findOne(id);

    }
}
