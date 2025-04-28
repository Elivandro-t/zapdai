package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.pedidos.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<PedidosEntity,Long> {
}
