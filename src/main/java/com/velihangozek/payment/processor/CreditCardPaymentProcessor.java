package com.velihangozek.payment.processor;

import com.velihangozek.payment.annotation.PaymentMethod;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;

@PaymentMethod("CREDIT_CARD")
public class CreditCardPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult(true, "Credit card payment processed successfully.");
    }
}