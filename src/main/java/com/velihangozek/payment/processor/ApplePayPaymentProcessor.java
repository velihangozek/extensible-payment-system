package com.velihangozek.payment.processor;

import com.velihangozek.payment.annotation.PaymentMethod;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;

@PaymentMethod("APPLE_PAY")
public class ApplePayPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult(true, "Apple Pay payment processed successfully.");
    }
}