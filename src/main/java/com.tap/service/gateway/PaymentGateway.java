package com.tap.service.gateway;

import com.tap.service.data.dto.paymentGateway.PaymentGatewayRequestDto;
import com.tap.service.data.dto.paymentGateway.PaymentGatewayResponseDto;

public interface PaymentGateway {
    PaymentGatewayResponseDto makePayment(PaymentGatewayRequestDto paymentGatewayRequestDto) throws Exception;

    PaymentGatewayResponseDto checkTransactionStatus(String transactionId);
    String getGatewayIdentifier();

}
