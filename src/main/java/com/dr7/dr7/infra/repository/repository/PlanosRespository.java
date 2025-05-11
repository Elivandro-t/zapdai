package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.planos.PlanosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlanosRespository extends JpaRepository<PlanosEntity,Long> {
    @Query("SELECT p FROM PlanosEntity p WHERE p.title = :title")
    Optional<PlanosEntity> findByTitulo(String title);
}
