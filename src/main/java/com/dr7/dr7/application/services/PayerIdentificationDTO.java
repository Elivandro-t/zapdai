package com.dr7.dr7.application.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PayerIdentificationDTO {
    @NotBlank
    private String type;
    @NotBlank
    private String number;

    public PayerIdentificationDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
