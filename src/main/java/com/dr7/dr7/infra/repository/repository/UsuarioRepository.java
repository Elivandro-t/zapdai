package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    @Query("Select p from UsuarioEntity p where p.email=:email or p.cpf=:cpf or p.phoneNumer = :numero")
Optional findOneByEmailAndByCpfAndByFone(String email, String cpf,String numero);

    @Query("Select p from UsuarioEntity p where p.clientId=:id")
    UsuarioEntity findOneById(Long id);
    @Query("SELECT u FROM UsuarioEntity u LEFT JOIN FETCH u.endereco WHERE u.email = :email")
    UsuarioEntity findOneByCpfEncontrado(String email);
    @Query("SELECT u FROM UsuarioEntity u LEFT JOIN FETCH u.roles WHERE u.email = :email")
    UsuarioEntity findOneByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String username);
}
