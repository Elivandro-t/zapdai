package com.dr7.dr7.infra.repository.repository;
import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;
import com.dr7.dr7.infra.repository.Entity.pedidos.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRespository extends JpaRepository<PerfilEntity,Long> {
    @Query("SELECT p FROM PerfilEntity p WHERE p.name=:roleUser and p.ativo=true")
    PerfilEntity findOnePerfilEntityByNameAndAtivoTrue(String roleUser);
}