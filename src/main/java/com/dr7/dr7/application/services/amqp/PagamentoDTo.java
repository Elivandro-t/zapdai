package com.dr7.dr7.application.services.amqp;

public record PagamentoDTo(  Long id,
                             String status,
                             String detail,
                             String date_approved,
                             String payment_method_i,
                             String payment_type_id,
                             String qrCodeBase64,
                             String qrCodeLink) {
}
