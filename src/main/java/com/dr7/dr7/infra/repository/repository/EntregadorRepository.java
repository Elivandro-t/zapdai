package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.pedidos.EntregadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregadorRepository extends JpaRepository<EntregadorEntity,Long> {
}
