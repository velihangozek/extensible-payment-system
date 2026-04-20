package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;

public abstract class AbstractPaymentHandler implements PaymentHandler {

    private PaymentHandler next;

    @Override
    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    protected void handleNext(PaymentContext context) {
        if (next != null) {
            next.handle(context);
        }
    }
}