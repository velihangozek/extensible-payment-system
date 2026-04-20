package com.velihangozek.payment.processor;

import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;

public interface PaymentProcessor {
    PaymentResult process(PaymentRequest request);
}