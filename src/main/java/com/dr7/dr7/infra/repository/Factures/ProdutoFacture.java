package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import com.dr7.dr7.gateways.ProdutoRepository;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import com.dr7.dr7.infra.repository.repository.CategoriaProdutosRepository;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import com.dr7.dr7.infra.repository.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoFacture implements ProdutoRepository {
    ProdutosRepository produtosRepository;
    @Value("${produtos}")
    private String endpoint;
    EmpresaRepository empresaRepository;
    private CategoriaProdutosRepository categoriaProdutosRepository;
    public ProdutoFacture (ProdutosRepository produtosRepository,
                           EmpresaRepository empresaRepository,
                           CategoriaProdutosRepository categoriaProdutosRepository
    ){
        this.produtosRepository = produtosRepository;
        this.empresaRepository = empresaRepository;
        this.categoriaProdutosRepository = categoriaProdutosRepository;
    }
    @Override
    public void saveProduct(ProdutoDTO produtodto, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        var path = "produtos";
        File pasta = new File(path);
        String nomeImagem = file.getOriginalFilename().replaceAll("\\s+", "_");
        String nome = path+"/"+nomeImagem;
        var imagemSalva = endpoint +"avatar/"+nomeImagem;
        if(!pasta.exists()){
            pasta.mkdirs();
        }
        Files.write(Paths.get(nome),bytes);
        CategoriaProdutos categoria = categoriaProdutosRepository.findById(produtodto.categoria().id()).orElseThrow(
                ()->new RuntimeException("Categoria não encontrada!")
        );
        var empresa =  empresaRepository.buscaEmpresaPorUsuario(produtodto.idEmpresa()).orElseThrow(()->new RuntimeException("Empresa invalida!"));
        List<ProdutosEntity> produtosEntities = new ArrayList<>();
        if(empresa!=null) {
            if (produtodto != null) {
                Produto produto = new Produto(produtodto);
                 empresa.setProdutos(produtosEntities);
               ProdutosEntity p = new ProdutosEntity(produto,empresa);
               p.setCategoria(categoria);
               p.setImgProduct(imagemSalva);
               empresaRepository.save(empresa);
            produtosRepository.save(p);
            }
        }
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        List<Produto> list = produtosRepository.findAll().stream().map(Produto::new).toList();
        var comp = list.stream().map(ProdutoResponseDTO::new).toList();
        return comp;
    }

    @Override
    public List<ProdutoResponseDTO> findOneProdutoFornecedo(String nome, String id) {
        List<Produto> list = produtosRepository.findAllByFornecedor(nome,id).stream().map(Produto::new).toList();
        if(list!=null){
            return list.stream().map(ProdutoResponseDTO::new).toList();
        }
        throw new RuntimeException("Informações não encontrada!");
    }
    public ProdutoResponseDTO findOne(Long id) {
        ProdutosEntity produto = produtosRepository.findByIdEMpres(id).orElseThrow(()->new RuntimeException("Produto não encontrado!"));
        if(produto!=null){
            return new ProdutoResponseDTO(new Produto(produto));
        }
        throw new RuntimeException("Informações não encontrada!");
    }
//    private String validaImagem(String pasta, String nomeImagem) throws IOException {
//        Path pathImagem = Paths.get(pasta, nomeImagem);
//        if (!Files.exists(pathImagem)) {
//            return nomeImagem;
//        }
//        String nome = nomeImagem;
//        String ext = "";
//        int idExt = nomeImagem.lastIndexOf(".");
//        if(idExt>0){
//            nome = nomeImagem.substring(0,idExt);
//            ext = nomeImagem.substring(idExt);
//        }
//        int contador = 1;
//        String novoNome;
//        do {
//            novoNome = String.format("%s(%d)%s",nome,contador,ext);
//            pathImagem = Paths.get(pasta, novoNome);
//            contador++;
//        }while (Files.exists(pathImagem));
//        return novoNome;
//
//    }
}
