package com.dr7.dr7.application.services.amqp;

public enum StatusPagamento {
    APROVADO,
    RECUSADO,
    AGUARDANDO_APROVAÇÃO,
    CANCELADO,
    PROCESSANDO_PAGAMENTO,
    PAGO
}
