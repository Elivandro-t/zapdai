package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.produtosEntity.CategoriaProdutos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProdutosRepository extends JpaRepository<CategoriaProdutos,Long> {
}
