package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;

public class PaymentLoggingHandler extends AbstractPaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        System.out.println("Payment method: " + context.getRequest().paymentMethodType());
        System.out.println("Amount: " + context.getRequest().amount());
        System.out.println("Currency: " + context.getRequest().currency());
        System.out.println("Result: " + context.getPaymentResult());

        handleNext(context);
    }
}