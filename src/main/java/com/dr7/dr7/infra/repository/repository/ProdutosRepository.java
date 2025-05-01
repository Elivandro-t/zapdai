package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.produtosEntity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ProdutosRepository extends JpaRepository<ProdutosEntity,Long> {
    @Query("SELECT p  FROM ProdutosEntity p WHERE p.nameEmpresa=:nome and p.idEmpresa=:id")
    List<ProdutosEntity> findAllByFornecedor(String nome, Long id);
}
