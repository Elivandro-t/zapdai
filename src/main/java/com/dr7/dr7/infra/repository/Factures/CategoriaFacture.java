package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.categorias.Categorias;
import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.gateways.CategoriaRepository;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import com.dr7.dr7.infra.repository.repository.CategoriaProdutosRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
public class CategoriaFacture implements CategoriaRepository {
   private CategoriaProdutosRepository categoriaProdutosRepository;
    @Value("${endpoint}")
    private String endpoint;
    public  CategoriaFacture(CategoriaProdutosRepository categoriaProdutosRepository){
        this.categoriaProdutosRepository = categoriaProdutosRepository;
    }
    @Override
    public void save(CategoriaDTO categoriadto, MultipartFile image) throws IOException {
        byte[] bytes = image.getBytes();
        String pasta = "categorias";
        File file = new File(pasta);
        String pah = pasta+"/"+image.getOriginalFilename().replaceAll("\\s+", "_");
        if(!file.exists()){
            file.mkdirs();
        }
        Files.write(Paths.get(pah),bytes);
        String name =endpoint+pah;
        Optional<CategoriaProdutos> resposta = categoriaProdutosRepository.findByNome(categoriadto.nome());
        if(resposta.isPresent()){
            throw new RuntimeException("Categoria já existe!");
        }
        Categorias categorias = new Categorias(categoriadto,name);
        CategoriaProdutos categoriaProdutos = new CategoriaProdutos(categorias);
        categoriaProdutosRepository.save(categoriaProdutos);
    }
    @Override
    public List<CategoriaDTO> lista() {
      var entidade =  categoriaProdutosRepository.findAll().stream().map(Categorias::new).toList();
        return entidade.stream().map(CategoriaDTO::new).toList();
    }
}
