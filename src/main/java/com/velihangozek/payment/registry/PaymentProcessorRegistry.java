package com.velihangozek.payment.registry;

import com.velihangozek.payment.processor.PaymentProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PaymentProcessorRegistry {

    private final Map<String, PaymentProcessor> processors = new HashMap<>();

    public void register(String paymentMethodType, PaymentProcessor processor) {
        processors.put(paymentMethodType, processor);
    }

    public Optional<PaymentProcessor> findProcessor(String paymentMethodType) {
        return Optional.ofNullable(processors.get(paymentMethodType));
    }
}