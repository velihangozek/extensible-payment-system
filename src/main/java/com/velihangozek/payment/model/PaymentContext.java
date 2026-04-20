package com.velihangozek.payment.model;

import com.velihangozek.payment.processor.PaymentProcessor;

public class PaymentContext {

    private final PaymentRequest request;
    private PaymentProcessor paymentProcessor;
    private PaymentResult paymentResult;

    public PaymentContext(PaymentRequest request) {
        this.request = request;
    }

    public PaymentRequest getRequest() {
        return request;
    }

    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public PaymentResult getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
    }
}