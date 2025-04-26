package com.dr7.dr7.infra.repository;

import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.infra.Entity.empresasEntity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long> {
}
