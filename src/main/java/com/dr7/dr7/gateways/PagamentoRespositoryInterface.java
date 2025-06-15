package com.dr7.dr7.gateways;

import com.dr7.dr7.application.services.amqp.PagamentoDTo;

public interface PagamentoRespositoryInterface {
    void save(PagamentoDTo pagamento);
}
