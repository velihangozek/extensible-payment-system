package com.velihangozek.payment;

import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;
import com.velihangozek.payment.registry.PaymentProcessorRegistry;
import com.velihangozek.payment.service.PaymentService;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        PaymentProcessorRegistry registry = new PaymentProcessorRegistry("com.velihangozek.payment");

        PaymentService paymentService = new PaymentService(registry);

        PaymentRequest request = new PaymentRequest(
                new BigDecimal("150.00"),
                "USD",
                "PAYPAL"
        );

        PaymentResult result = paymentService.processPayment(request);

        System.out.println("Final result: " + result);
    }
}