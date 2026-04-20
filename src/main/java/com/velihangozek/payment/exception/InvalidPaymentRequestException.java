package com.velihangozek.payment.exception;

public class InvalidPaymentRequestException extends PaymentException {

    public InvalidPaymentRequestException(String message) {
        super(message);
    }
}