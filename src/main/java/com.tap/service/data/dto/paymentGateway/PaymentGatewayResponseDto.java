package com.tap.service.data.dto.paymentGateway;

import com.tap.service.data.dto.enums.Status;

import java.util.UUID;

public class PaymentGatewayResponseDto {
    UUID transactionId;
    Status status;

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
