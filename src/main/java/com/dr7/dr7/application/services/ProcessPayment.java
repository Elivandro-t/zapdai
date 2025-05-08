package com.dr7.dr7.application.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ProcessPayment {
    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken;

    public PaymentResponseDTO processPayment(CardPaymentDTO request, String token) {
        try {
            String uniqueValue = System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", uniqueValue);

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            MercadoPagoConfig.setAccessToken(token);

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(request.getTransactionAmount())
                            .token(request.getToken())
                            .description(request.getProductDescription())
                            .installments(request.getInstallments())
                            .paymentMethodId(request.getPaymentMethodId())
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(request.getPayer().getEmail())
                                            .firstName(request.getPayer().getFirst_name())
                                            .lastName(request.getPayer().getLast_name())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(request.getPayer().getIdentification().getType())
                                                            .number(request.getPayer().getIdentification().getNumber())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest,requestOptions);

            return new PaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getDateApproved(),
                    createdPayment.getPaymentMethodId(),
                    createdPayment.getPaymentTypeId()
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
