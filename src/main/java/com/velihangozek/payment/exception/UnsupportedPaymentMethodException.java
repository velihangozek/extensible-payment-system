package com.velihangozek.payment.exception;

public class UnsupportedPaymentMethodException extends PaymentException {

    public UnsupportedPaymentMethodException(String message) {
        super(message);
    }
}