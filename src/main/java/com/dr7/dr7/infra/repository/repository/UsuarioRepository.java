package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    @Query("Select p from UsuarioEntity p where p.email=:email or p.cpf=:cpf")
    Optional findOneByEmailAndByCpf(String email, String cpf);

    @Query("Select p from UsuarioEntity p where p.clientId=:id and cpf=:cpf")
    UsuarioEntity findOneById(Long id,String cpf);

    UsuarioEntity findOneByCpf(String cpf);
    @Query("Select p from UsuarioEntity p where p.email=:email")
    UsuarioEntity findOneByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String username);
}
