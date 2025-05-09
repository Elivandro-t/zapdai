package com.dr7.dr7.application.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProcessPayment {

    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken;

    public PaymentResponseDTO processPayment(CardPaymentDTO request, String token) {
        try {
            MercadoPagoConfig.setAccessToken(token);
            String uniqueValue = System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", uniqueValue);

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();
            PaymentClient paymentClient = new PaymentClient();
            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(request.getTransactionAmount())
                    .issuerId(request.getIssuerId())// Valor da transação
                    .token(request.getToken()) // Token do cartão ou PIX
                    .description(request.getProductDescription()) // Descrição do produto
                    .installments(request.getInstallments()) // Parcelas
                    .paymentMethodId(request.getPaymentMethodId())
                    .statementDescriptor("zapdai")
                    .additionalInfo(paymentAdditionalInfoRequest(request))
                    .notificationUrl("https://zapdai-zmo0.onrender.com/process_payment/webhook/mercadopago")
                    .externalReference("pedido-" + UUID.randomUUID())
                    .payer(PaymentPayerRequest.builder()
                            .email(request.getPayer().getEmail()) // Email do pagador
                            .firstName(request.getPayer().getFirst_name()) // Nome do pagador
                            .lastName(request.getPayer().getLast_name())
                            .identification(
                                    IdentificationRequest.builder()
                                            .type(request.getPayer().getIdentification().getType()) // Tipo de identificação (CPF, CNPJ)
                                            .number(request.getPayer().getIdentification().getNumber()) // Número de identificação
                                            .build()
                            )

                            .build())
                    .build()
                    ;

            Payment createdPayment = paymentClient.create(paymentCreateRequest, requestOptions);

            String qrCodeBase = null;
            String qrCodeLink = null;

            if ("pix".equalsIgnoreCase(request.getPaymentMethodId())) {
                if (createdPayment.getPointOfInteraction() != null &&
                        createdPayment.getPointOfInteraction().getTransactionData() != null) {

                    qrCodeBase = createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64();
                    qrCodeLink = createdPayment.getPointOfInteraction().getTransactionData().getQrCode(); // código Pix copia e cola
                    // ou: getTicketUrl() se disponível
                } else {
                    System.out.println("QR Code não retornado. Verifique se sua conta está com Pix ativado.");
                }
            }
            return new PaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getDateApproved(),
                    createdPayment.getPaymentMethodId(),
                    createdPayment.getPaymentTypeId(),
                    qrCodeBase,
                    qrCodeLink
            );
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new RuntimeException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }
    private PaymentAdditionalInfoRequest paymentAdditionalInfoRequest(CardPaymentDTO card){
        PaymentAdditionalInfoPayerRequest payer = PaymentAdditionalInfoPayerRequest.builder()
                .firstName(card.getPayer().getFirst_name())
                .lastName(card.getPayer().getLast_name())
                .build();
        List<PaymentItemRequest> itemRequests = Optional.ofNullable(card.getItens())
                .orElse(Collections.emptyList())
                .stream()
                .map(item -> PaymentItemRequest.builder()
                        .id(item.getId())
                        .title(item.getTitle())
                        .description(item.getDescription())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getPrice())
                        .build())
                .collect(Collectors.toList());

        return PaymentAdditionalInfoRequest.builder()
                .items(itemRequests)
                .payer(payer)
                .build();
    }
}
