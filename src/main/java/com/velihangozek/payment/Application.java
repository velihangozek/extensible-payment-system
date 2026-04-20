package com.velihangozek.payment;

import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;
import com.velihangozek.payment.processor.CreditCardPaymentProcessor;
import com.velihangozek.payment.processor.PaypalPaymentProcessor;
import com.velihangozek.payment.registry.PaymentProcessorRegistry;
import com.velihangozek.payment.service.PaymentService;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        PaymentProcessorRegistry registry = new PaymentProcessorRegistry();
        registry.register("CREDIT_CARD", new CreditCardPaymentProcessor());
        registry.register("PAYPAL", new PaypalPaymentProcessor());

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