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

@Service
public class ProcessPayment {

    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken;

    public PaymentResponseDTO processPayment(CardPaymentDTO request, String token) {
        try {

            String uniqueValue = System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", uniqueValue);
            List<PaymentItemRequest> itemsList = new ArrayList<>();
//            PaymentRouteRequest route = PaymentRouteRequest.builder()
//                    .departure("São Paulo")
//                    .destination("Rio de Janeiro")
//                    .departureDateTime(OffsetDateTime.parse("2020-08-06T09:25:04.000-03:00"))
//                    .arrivalDateTime(OffsetDateTime.parse("2020-08-06T09:25:04.000-03:00"))
//                    .company("Company")
//                    .build();
            PaymentCategoryDescriptorRequest categoryDescriptor = PaymentCategoryDescriptorRequest.builder()
//                    .route(route)
                    .build();
            PaymentItemRequest item = PaymentItemRequest.builder()
                    .id("1941")
                    .title("Pao de forma")
                    .description("pao que voce come comm café")
                    .pictureUrl("pictureUrl")
                    .categoryId("1")
                    .quantity(1)
                    .unitPrice(new BigDecimal("10"))
                    .eventDate(LocalDateTime.now().atOffset(ZoneOffset.of("-03:00")))
                    .warranty(true)
                    .categoryDescriptor(categoryDescriptor)
                    .build();
            itemsList.add(item);

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            MercadoPagoConfig.setAccessToken(token);
            PaymentAdditionalInfoRequest additional = PaymentAdditionalInfoRequest.builder()
                    .items(itemsList)
                    .build();

            PaymentClient paymentClient = new PaymentClient();
            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(request.getTransactionAmount()) // Valor da transação
                    .token(request.getToken()) // Token do cartão ou PIX
                    .description(request.getProductDescription()) // Descrição do produto
                    .installments(request.getInstallments()) // Parcelas
                    .paymentMethodId(request.getPaymentMethodId())
                    .additionalInfo(additional)
                    .payer(PaymentPayerRequest.builder()
                            .email(request.getPayer().getEmail()) // Email do pagador
                            .firstName(request.getPayer().getFirst_name()) // Nome do pagador
                            .lastName(request.getPayer().getLast_name()) // Sobrenome do pagador
                            .identification(
                                    IdentificationRequest.builder()
                                            .type(request.getPayer().getIdentification().getType()) // Tipo de identificação (CPF, CNPJ)
                                            .number(request.getPayer().getIdentification().getNumber()) // Número de identificação
                                            .build())
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
}
