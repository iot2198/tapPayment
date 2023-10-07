package com.tap.service.data.dto.paymentGateway;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PaymentGatewayRequestDto {
    @NotNull
    UUID payerId;
    @NotNull
    UUID payeeId;
    @NotNull
    @Min(1)
    Double amount;
    @NotNull
    String currency;
    @NotNull
    String preferredGateway;

    @NotNull
    UUID transactionId;

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
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

    public String getPreferredGateway() {
        return preferredGateway;
    }

    public void setPreferredGateway(String preferredGateway) {
        this.preferredGateway = preferredGateway;
    }

}
