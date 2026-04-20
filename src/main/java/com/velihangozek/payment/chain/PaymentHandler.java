package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;

public interface PaymentHandler {
    void handle(PaymentContext context);
    void setNext(PaymentHandler next);
}