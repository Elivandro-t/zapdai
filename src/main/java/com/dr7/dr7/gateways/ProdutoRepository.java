package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.vo.produtodto.ProdutoDTO;
import com.dr7.dr7.domain.vo.produtodto.ProdutoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProdutoRepository {
    void saveProduct(ProdutoDTO produto, MultipartFile file) throws IOException;
    List<ProdutoResponseDTO> findAll();
    List<ProdutoResponseDTO> findOneProdutoFornecedo(String nome, String id);
    ProdutoResponseDTO findOne(Long id);

}
