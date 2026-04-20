package com.velihangozek.payment.chain;

import com.velihangozek.payment.exception.UnsupportedPaymentMethodException;
import com.velihangozek.payment.model.PaymentContext;
import com.velihangozek.payment.processor.PaymentProcessor;
import com.velihangozek.payment.registry.PaymentProcessorRegistry;

public class PaymentMethodResolutionHandler extends AbstractPaymentHandler {

    private final PaymentProcessorRegistry paymentProcessorRegistry;

    public PaymentMethodResolutionHandler(PaymentProcessorRegistry paymentProcessorRegistry) {
        this.paymentProcessorRegistry = paymentProcessorRegistry;
    }

    @Override
    public void handle(PaymentContext context) {
        String paymentMethodType = context.getRequest().paymentMethodType();

        PaymentProcessor paymentProcessor = paymentProcessorRegistry.findProcessor(paymentMethodType)
                .orElseThrow(() -> new UnsupportedPaymentMethodException(
                        "Unsupported payment method: " + paymentMethodType
                ));

        context.setPaymentProcessor(paymentProcessor);
        handleNext(context);
    }
}