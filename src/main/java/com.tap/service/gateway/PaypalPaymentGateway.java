package com.tap.service.gateway;

import com.tap.service.data.dto.enums.Status;
import com.tap.service.data.dto.paymentGateway.PaymentGatewayRequestDto;
import com.tap.service.data.dto.paymentGateway.PaymentGatewayResponseDto;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@Component
public class PaypalPaymentGateway implements PaymentGateway {
    private final Random random = new Random();

    public PaypalPaymentGateway() {
    }

    @Override
    public PaymentGatewayResponseDto makePayment(PaymentGatewayRequestDto paymentGatewayRequestDto) throws Exception {
        if (shouldFailRandomly()) {
            throw new TimeoutException("Timeout");
        }

        PaymentGatewayResponseDto response = new PaymentGatewayResponseDto();
        response.setStatus(Status.Success);
        response.setTransactionId(paymentGatewayRequestDto.getTransactionId());
        return response;
    }

    @Override
    public PaymentGatewayResponseDto checkTransactionStatus(String transactionId) {
        return new PaymentGatewayResponseDto();
    }


    private boolean shouldFailRandomly() {
        double failureProbability = 0.5;
        return random.nextDouble() < failureProbability;
    }

    @Override
    public String getGatewayIdentifier() {
        return "Paypal";
    }
}
