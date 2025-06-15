package com.dr7.dr7.application.services.amqp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PagamentoDTo(
                           StatusPagamento statusPagoPlano,
                           boolean statusPago,
                           String dateCreated, String planoId,
                           String planoName,
                           String PagamentoRef,
                           String userName,
                           String email,
                           BigDecimal valorPago,
                           String formaDePagamento
) {
}
