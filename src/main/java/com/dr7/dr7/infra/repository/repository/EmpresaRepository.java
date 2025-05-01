package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long> {
    @Query("select p from EmpresaEntity p where p.razaoSocial=:s or p.numeroDeTelefone=:telefone or email=:email")
    EmpresaEntity findOneByRazaoSocial( String s,String telefone,String email);
}
