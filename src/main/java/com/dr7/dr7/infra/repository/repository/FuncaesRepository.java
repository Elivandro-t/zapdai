package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.funcoes.FuncaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncaesRepository extends JpaRepository<FuncaoEntity,String> {
    @Query("SELECT p FROM FuncaoEntity p left join fetch p.children where p.ativo = true")
    List<FuncaoEntity> findAllByAtivoTrue();
}
