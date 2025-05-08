package com.dr7.dr7.application.services;

import java.time.OffsetDateTime;

public class PaymentResponseDTO {
    private Long id;
    private String status;
    private String detail;
    private String date_approved;
    private String payment_method_id;
    private String payment_type_id;
    private PayerDTO payer;


    public PaymentResponseDTO(Long id, String status, String detail, OffsetDateTime date_approved, String payment_method_id, String payment_type_id) {
        this.id = id;
        this.status = status;
        this.detail = detail;
        this.date_approved = String.valueOf(date_approved);
        this.payment_method_id = payment_method_id;
        this.payment_type_id = payment_type_id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate_approved() {
        return date_approved;
    }

    public PayerDTO getPayer() {
        return payer;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }
}

