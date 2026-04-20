package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;

public class PaymentMethodResolutionHandler extends AbstractPaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        handleNext(context);
    }
}