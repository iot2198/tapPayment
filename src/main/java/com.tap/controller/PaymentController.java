package com.tap.controller;

import com.tap.service.data.dto.controller.InitiatePaymentRequestDto;
import com.tap.service.data.dto.controller.InitiatePaymentResponseDto;
import com.tap.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/payments/initiate")
    public ResponseEntity<InitiatePaymentResponseDto> initiatePayment(@Valid @RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) throws Exception {
        InitiatePaymentResponseDto initiatePaymentResponseDto = paymentService.processTransaction(initiatePaymentRequestDto);
        return ResponseEntity.ok(initiatePaymentResponseDto);
    }
}
