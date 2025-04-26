package com.dr7.dr7.infra.repository;

import com.dr7.dr7.infra.Entity.pedidos.EntregadorEntity;
import com.dr7.dr7.infra.Entity.produtosEntity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregadorRepository extends JpaRepository<EntregadorEntity,Long> {
}
