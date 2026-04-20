package com.velihangozek.payment.chain;

import com.velihangozek.payment.exception.InvalidPaymentRequestException;
import com.velihangozek.payment.model.PaymentContext;
import com.velihangozek.payment.model.PaymentRequest;

import java.math.BigDecimal;

public class PaymentValidationHandler extends AbstractPaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        PaymentRequest request = context.getRequest();

        if (request == null) {
            throw new InvalidPaymentRequestException("Payment request cannot be null.");
        }

        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentRequestException("Payment amount must be greater than zero.");
        }

        if (request.currency() == null || request.currency().isBlank()) {
            throw new InvalidPaymentRequestException("Currency cannot be blank.");
        }

        if (request.paymentMethodType() == null) {
            throw new InvalidPaymentRequestException("Payment method type cannot be null.");
        }

        handleNext(context);
    }
}