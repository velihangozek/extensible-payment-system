package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;

public class PaymentExecutionHandler extends AbstractPaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        handleNext(context);
    }
}