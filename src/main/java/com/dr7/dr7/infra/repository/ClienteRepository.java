package com.dr7.dr7.infra.repository;

import com.dr7.dr7.infra.Entity.cliente.ClienteEntity;
import com.dr7.dr7.infra.Entity.produtosEntity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
}
