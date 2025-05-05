package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import com.dr7.dr7.gateways.ProdutoRepository;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import com.dr7.dr7.infra.repository.repository.ProdutosRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoFacture implements ProdutoRepository {
    ProdutosRepository produtosRepository;
    EmpresaRepository empresaRepository;
    public ProdutoFacture (ProdutosRepository produtosRepository,EmpresaRepository empresaRepository){
        this.produtosRepository = produtosRepository;
        this.empresaRepository = empresaRepository;
    }
    @Override
    public void saveProduct(ProdutoDTO produtodto) {
        var empresa =  empresaRepository.buscaEmpresaPorUsuario(produtodto.idEmpresa(),produtodto.nameEmpresa());
        if(empresa.isPresent()) {
            if (produtodto != null) {
                Produto produto = new Produto(produtodto);
            produtosRepository.save(new ProdutosEntity(produto));
            }
        }
        throw new RuntimeException("Empresa invalida!");
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        List<Produto> list = produtosRepository.findAll().stream().map(Produto::new).toList();
        var comp = list.stream().map(ProdutoResponseDTO::new).toList();
        return comp;
    }

    @Override
    public List<ProdutoResponseDTO> findOneProdutoFornecedo(String nome, Long id) {
        List<Produto> list = produtosRepository.findAllByFornecedor(nome,id).stream().map(Produto::new).toList();
        if(list!=null){
            return list.stream().map(ProdutoResponseDTO::new).toList();
        }
        throw new RuntimeException("Informações não encontrada!");
    }
}
