package com.dr7.dr7.infra.repository;

import com.dr7.dr7.infra.Entity.pedidos.ItensPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itensPedidosRepository extends JpaRepository<ItensPedidos,Long> {
}
