package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
}
