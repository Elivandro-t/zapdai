package com.dr7.dr7.domain.vo.produtodto;

import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaProdutoDTO;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoResponseDTO(
        long idProduto,
        String imgProduct,
         String productName,
         Float price,
         Float peso,
         Long categoriaProduct,
         String description,
         Integer amountQTD,
        EmpresaRespostaProdutoDTO empresaDTO
) {
    public ProdutoResponseDTO(Produto item) {
        this(item.getIdProduto(),
                item.getImgProduct(),
                item.getNomeProduto()
                ,item.getPreco(),item.getPeso(),
                item.getCategoriaProdutos(),item.getDescricao(),
                item.getQuantidade(),
                new EmpresaRespostaProdutoDTO(item.getEmpresa())
        )
        ;
    }
}
