package com.dr7.dr7.infra.repository;

import com.dr7.dr7.infra.Entity.produtosEntity.CategoriaProdutos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProdutosRepository extends JpaRepository<CategoriaProdutos,Long> {
}
