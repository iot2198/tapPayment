package com.tap.service.gateway;

import com.tap.service.data.dto.paymentGateway.PaymentGatewayRequestDto;
import com.tap.service.data.dto.paymentGateway.PaymentGatewayResponseDto;
import com.tap.service.data.dto.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentGatewayManager {
    private List<PaymentGateway> gateways;

    private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayManager.class);

    public PaymentGatewayManager() {
        gateways = new ArrayList<>();

        PaymentGateway paypalGateway = new PaypalPaymentGateway();
        PaymentGateway stripeGateway = new StripePaymentGateway();

        gateways.add(paypalGateway);
        gateways.add(stripeGateway);
    }

    public PaymentGatewayResponseDto processTransaction(PaymentGatewayRequestDto paymentGatewayRequestDto) throws Exception {
        String preferredGatewayName = paymentGatewayRequestDto.getPreferredGateway();

        PaymentGateway preferredGateway = null;
        for (PaymentGateway gateway : gateways) {
            if (gateway.getGatewayIdentifier().equals(preferredGatewayName)) {
                preferredGateway = gateway;
                break;
            }
        }

        if (preferredGateway != null) {
            try {
                PaymentGatewayResponseDto response = preferredGateway.makePayment(paymentGatewayRequestDto);
                if (response.getStatus() == Status.Success) {
                    return response;
                }
            } catch (Exception e) {
                logger.warn(String.valueOf(e));
            }
        }

        for (PaymentGateway gateway : gateways) {
            if (gateway != preferredGateway) {
                try {
                    PaymentGatewayResponseDto response = gateway.makePayment(paymentGatewayRequestDto);
                    if (response.getStatus() == Status.Success) {
                        return response;
                    }
                } catch (Exception e) {
                    logger.warn(String.valueOf(e));
                }
            }
        }

        throw new Exception("All payment gateways failed.");
    }
}
