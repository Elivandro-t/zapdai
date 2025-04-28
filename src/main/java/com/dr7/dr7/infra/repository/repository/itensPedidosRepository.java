package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.pedidos.ItensPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itensPedidosRepository extends JpaRepository<ItensPedidos,Long> {
}
