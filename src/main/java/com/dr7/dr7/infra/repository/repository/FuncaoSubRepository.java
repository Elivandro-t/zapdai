package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.infra.repository.Entity.funcoes.FuncaoSub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncaoSubRepository extends JpaRepository<FuncaoSub,String> {
}
