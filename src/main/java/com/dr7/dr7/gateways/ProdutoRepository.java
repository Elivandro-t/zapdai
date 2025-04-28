package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoRepository {
    void saveProduct(ProdutoDTO produto);
    List<ProdutoResponseDTO> findAll();
    List<ProdutoResponseDTO> findOneProdutoFornecedo(String nome, Long id);

}
