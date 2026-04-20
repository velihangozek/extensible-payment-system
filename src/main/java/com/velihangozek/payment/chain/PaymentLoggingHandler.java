package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;

public class PaymentLoggingHandler extends AbstractPaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        handleNext(context);
    }
}