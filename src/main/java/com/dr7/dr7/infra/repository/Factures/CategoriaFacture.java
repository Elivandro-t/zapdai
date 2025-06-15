package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.categorias.Categorias;
import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.gateways.CategoriaRepository;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import com.dr7.dr7.infra.repository.repository.CategoriaProdutosRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
        String nomeImagem = image.getOriginalFilename().replaceAll("\\s+", "_");
        String pah = pasta+"/"+nomeImagem;
        validaImagem(pasta,nomeImagem);
        if(!file.exists()){
            file.mkdirs();
        }
//        Files.write(Paths.get(pah),bytes);
        removerFundo(image,pah);
        String name =endpoint+pasta+"/imagens/"+nomeImagem;
        Optional<CategoriaProdutos> resposta = categoriaProdutosRepository.findByNome(categoriadto.nome());
        if(resposta.isPresent()){
            throw new RuntimeException("Categoria já existe!");
        }
        Categorias categorias = new Categorias(categoriadto,name);
        CategoriaProdutos categoriaProdutos = new CategoriaProdutos(categorias);
        categoriaProdutosRepository.save(categoriaProdutos);
    }

    private void validaImagem(String pasta, String nomeImagem) throws IOException {
        Path pathImagem = Paths.get(pasta, nomeImagem);
        if (Files.exists(pathImagem)) {
            throw new IOException("Já existe uma imagem com esse  mesmo nome: " + nomeImagem);
        }
    }

    @Override
    public List<CategoriaDTO> lista() {
      var entidade =  categoriaProdutosRepository.findAll().stream().map(Categorias::new).toList();
        return entidade.stream().map(CategoriaDTO::new).toList();
    }
    private void removerFundo(MultipartFile image, String outputPath) throws IOException {
        String apiKey = "rh3V4NCxggJ2rie6TttR4kg5";

        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("image_file", image.getInputStream(), ContentType.DEFAULT_BINARY, image.getOriginalFilename())
                .addTextBody("size", "auto")
                .build();

        Request.Post("https://api.remove.bg/v1.0/removebg")
                .addHeader("X-Api-Key", apiKey)
                .body(entity)
                .execute()
                .saveContent(new File(outputPath));
    }

    @Override
    public void deleteCategoria(String nome) throws URISyntaxException {
     String diretory = "categorias";
      var existe =  categoriaProdutosRepository.findByNome(nome);
      if (!existe.isPresent()){
          throw  new RuntimeException("categoria não encontrada!");
      }
       String url = existe.get().getIcone();
        String nameImagem = Paths.get(new URI(url).getPath()).getFileName().toString();
        Path path = Paths.get(diretory).resolve(nameImagem);
        if(Files.exists(path)){
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao deletar a imagem: " + e.getMessage());
            }
        }
      categoriaProdutosRepository.delete(existe.get());
    }

}
