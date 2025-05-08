package com.dr7.dr7.application.services;

import jakarta.validation.constraints.NotNull;

public class PayerDTO {
    @NotNull
    private String email;
    private String clerar;
    private String last_name;
    private String type;

    @NotNull
    private PayerIdentificationDTO identification;

    public PayerDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PayerIdentificationDTO getIdentification() {
        return identification;
    }

    public void setIdentification(PayerIdentificationDTO identification) {
        this.identification = identification;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getType() {
        return type;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
