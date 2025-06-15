package com.dr7.dr7.application.services;

import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.gateways.CategoriaRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaService {
    CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }
    public void saveCategoria(CategoriaDTO categoria, MultipartFile icon) throws IOException {
        categoriaRepository.save(categoria,icon);
    }
   public Map<String,List<CategoriaDTO>> lista(){
        Map<String,List<CategoriaDTO>> listMap = new HashMap<>();
        var lista = categoriaRepository.lista();
        listMap.put("categorias",lista);
      return listMap;
    }
    String pasta = "categorias";
    public ResponseEntity<Resource> ListaImagensId(String name){
        Path path = Paths.get(pasta).resolve(name);
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
    public void delete(String name) throws URISyntaxException {
        categoriaRepository.deleteCategoria(name);
    }
}
