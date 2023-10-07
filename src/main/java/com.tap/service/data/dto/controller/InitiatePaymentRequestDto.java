package com.tap.service.data.dto.controller;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class InitiatePaymentRequestDto {
    @NotNull
    UUID payerId;
    @NotNull
    UUID payeeId;
    @NotNull
    @DecimalMin(value = "1.0", message = "Amount must be greater than or equal to 1.0")
    Double amount;
    @NotNull
    String currency;

    @NotNull
    UUID transactionId;
    @NotNull
    String preferredGateway;


    public String getPreferredGateway() {
        return preferredGateway;
    }

    public void setPreferredGateway(String preferredGateway) {
        this.preferredGateway = preferredGateway;
    }

    public UUID getPayerId() {
        return payerId;
    }

    public void setPayerId(UUID payerId) {
        this.payerId = payerId;
    }

    public UUID getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(UUID payeeId) {
        this.payeeId = payeeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
}


