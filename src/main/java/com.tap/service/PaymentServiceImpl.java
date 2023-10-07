package com.tap.service;

import com.tap.service.data.dao.*;
import com.tap.service.data.dto.controller.InitiatePaymentRequestDto;
import com.tap.service.data.dto.controller.InitiatePaymentResponseDto;
import com.tap.service.data.dto.enums.PaymentGatewayEnum;
import com.tap.service.data.dto.enums.Status;
import com.tap.service.data.dto.paymentGateway.PaymentGatewayRequestDto;
import com.tap.service.data.dto.paymentGateway.PaymentGatewayResponseDto;
import com.tap.service.gateway.PaymentGatewayManager;
import com.tap.service.utils.InvalidRequestException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentGatewayManager paymentGatewayManager;

    @Autowired
    PaymentRepository paymentRepository;


    @Autowired
    PayeeIdRepository payeeIdRepository;

    @Autowired
    PayerIdRepository payerIdRepository;

    @Autowired
    CurrencyRepository currencyRepository;


    @Override
    public InitiatePaymentResponseDto processTransaction(InitiatePaymentRequestDto initiatePaymentRequestDto) throws Exception {
        validatePaymentRequest(initiatePaymentRequestDto);
        if (checkUniqueTransaction(initiatePaymentRequestDto)){
            insertTransaction(initiatePaymentRequestDto);
            PaymentGatewayResponseDto response = paymentGatewayManager.processTransaction(controllerRequestToGateway(initiatePaymentRequestDto));
            if (response.getStatus().equals(Status.Success)){
                UpdateStatus(response);
                return gatewayToController(response);
            }

        }
        else {
            throw new Exception("Repeated Transaction");
        }



        return null;
    }

    public PaymentGatewayRequestDto controllerRequestToGateway(InitiatePaymentRequestDto initiatePaymentRequestDto){
        PaymentGatewayRequestDto paymentGatewayRequestDto = new PaymentGatewayRequestDto();
        paymentGatewayRequestDto.setAmount(initiatePaymentRequestDto.getAmount());
        paymentGatewayRequestDto.setPayeeId(initiatePaymentRequestDto.getPayeeId());
        paymentGatewayRequestDto.setPayerId(initiatePaymentRequestDto.getPayerId());
        paymentGatewayRequestDto.setPreferredGateway(initiatePaymentRequestDto.getPreferredGateway());
        paymentGatewayRequestDto.setCurrency(initiatePaymentRequestDto.getCurrency());
        paymentGatewayRequestDto.setTransactionId(initiatePaymentRequestDto.getTransactionId());
        return paymentGatewayRequestDto;
    }

    public InitiatePaymentResponseDto gatewayToController(PaymentGatewayResponseDto paymentGatewayResponseDto){
        InitiatePaymentResponseDto initiatePaymentResponseDto = new InitiatePaymentResponseDto();
        initiatePaymentResponseDto.setTransactionId(paymentGatewayResponseDto.getTransactionId());
        initiatePaymentResponseDto.setStatus(paymentGatewayResponseDto.getStatus());
        return initiatePaymentResponseDto;
    }

    public Boolean checkUniqueTransaction(InitiatePaymentRequestDto initiatePaymentRequestDto){
        return paymentRepository.doesNotExistByTransactionId(initiatePaymentRequestDto.getTransactionId());

    }

    public void insertTransaction(InitiatePaymentRequestDto initiatePaymentRequestDto){
        paymentRepository.save(toTransaction(initiatePaymentRequestDto));
    }

    private Payment toTransaction(InitiatePaymentRequestDto initiatePaymentRequestDto){
        Payment transaction = new Payment();
        transaction.setTransactionId(initiatePaymentRequestDto.getTransactionId());
        transaction.setAmount(initiatePaymentRequestDto.getAmount());
        transaction.setCurrency(initiatePaymentRequestDto.getCurrency());
        transaction.setPayeeId(initiatePaymentRequestDto.getPayeeId());
        transaction.setPayerId(initiatePaymentRequestDto.getPayerId());
        transaction.setPrefferedGateway(initiatePaymentRequestDto.getPreferredGateway());
        transaction.setTransactionId(initiatePaymentRequestDto.getTransactionId());
        transaction.setSuccess(false);
        return transaction;
    }


    private void UpdateStatus(PaymentGatewayResponseDto paymentGatewayResponseDto){
        paymentRepository.update(paymentGatewayResponseDto.getTransactionId());
    }

    public void validatePaymentRequest(InitiatePaymentRequestDto requestDto) throws InvalidRequestException {
        payerIdRepository.existsById(requestDto.getPayerId());
        payeeIdRepository.existsById(requestDto.getPayeeId());
        currencyRepository.existsById(requestDto.getCurrency());


        validatePreferredGateway(requestDto.getPreferredGateway());

    }

    public void validatePreferredGateway(String preferredGateway) throws InvalidRequestException {
        if (preferredGateway == null || preferredGateway.isEmpty()) {
            throw new InvalidRequestException("Invalid preferred gateway.");
        }
        try {
            PaymentGatewayEnum.valueOf(preferredGateway);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Invalid preferred gateway.");
        }
    }
}
