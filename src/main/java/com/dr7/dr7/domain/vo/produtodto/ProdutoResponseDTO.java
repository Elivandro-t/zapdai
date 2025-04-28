package com.dr7.dr7.domain.vo.produtodto;

import com.dr7.dr7.domain.produto.Produto;
import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoResponseDTO(
        String imgProduct,
         String productName,
         long fornecedoId,
         String FornecedorName,
         Float price,
         Float peso,
         Long categoriaProduct,
         String description,
         Integer amountQTD,
        String categoriaProdutosName
) {
    public ProdutoResponseDTO(Produto item) {
        this(item.getImgProduct(),
                item.getNomeProduto(),item.getFornecedoId(),
                item.getNomeFornecedo(),item.getPreco(),item.getPeso(),
                item.getCategoriaProdutos(),item.getDescricao(),
                item.getQuantidade(),item.getCategoriaProdutosName());
    }
}
