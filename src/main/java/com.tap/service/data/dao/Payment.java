package com.tap.service.data.dao;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "payment")
public class Payment implements Serializable{

    private static final long serialVersionUID = 123765351514001L;
    @Id
    @Column(name = "transaction_id")
    UUID transactionId;

    @NotNull
    @Column(name = "payer_id")
    UUID payerId;

    @NotNull
    @Column(name = "payee_id")
    UUID payeeId;

    @NotNull
    @Column(name = "amount")
    Double amount;

    @NotNull
    @Column(name = "currency")
    String currency;

    @NotNull
    @Column(name = "preffered_gateway")
    String prefferedGateway;


    @Column(name = "success")
    Boolean success;

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

    public String getPrefferedGateway() {
        return prefferedGateway;
    }

    public void setPrefferedGateway(String prefferedGateway) {
        this.prefferedGateway = prefferedGateway;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}


