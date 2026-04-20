package com.velihangozek.payment.processor;

import com.velihangozek.payment.annotation.PaymentMethod;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;

@PaymentMethod("PAYPAL")
public class PaypalPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult(true, "PayPal payment processed successfully.");
    }
}