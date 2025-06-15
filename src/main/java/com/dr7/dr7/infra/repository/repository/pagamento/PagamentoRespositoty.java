package com.dr7.dr7.infra.repository.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PagamentoRespositoty extends JpaRepository<Pagamento,String> {
    @Query("SELECT p FROM Pagamento p where p.email=:email")
    Optional<Pagamento> findOneByEmail(String email);
}
