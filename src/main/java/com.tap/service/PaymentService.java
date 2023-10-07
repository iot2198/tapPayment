package com.tap.service;

import com.tap.service.data.dto.controller.InitiatePaymentRequestDto;
import com.tap.service.data.dto.controller.InitiatePaymentResponseDto;

public interface PaymentService {
    InitiatePaymentResponseDto processTransaction(InitiatePaymentRequestDto initiatePaymentRequestDto) throws Exception;
}
