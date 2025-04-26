package com.dr7.dr7.infra.repository;

import com.dr7.dr7.infra.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.Entity.produtosEntity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<ProdutosEntity,Long> {
}
