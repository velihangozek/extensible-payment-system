package com.velihangozek.payment.service;

import com.velihangozek.payment.chain.PaymentExecutionHandler;
import com.velihangozek.payment.chain.PaymentLoggingHandler;
import com.velihangozek.payment.chain.PaymentMethodResolutionHandler;
import com.velihangozek.payment.chain.PaymentValidationHandler;
import com.velihangozek.payment.model.PaymentContext;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;
import com.velihangozek.payment.registry.PaymentProcessorRegistry;

public class PaymentService {

    private final PaymentProcessorRegistry paymentProcessorRegistry;

    public PaymentService(PaymentProcessorRegistry paymentProcessorRegistry) {
        this.paymentProcessorRegistry = paymentProcessorRegistry;
    }

    public PaymentResult processPayment(PaymentRequest request) {
        PaymentValidationHandler validationHandler = new PaymentValidationHandler();
        PaymentMethodResolutionHandler resolutionHandler =
                new PaymentMethodResolutionHandler(paymentProcessorRegistry);
        PaymentExecutionHandler executionHandler = new PaymentExecutionHandler();
        PaymentLoggingHandler loggingHandler = new PaymentLoggingHandler();

        validationHandler.setNext(resolutionHandler);
        resolutionHandler.setNext(executionHandler);
        executionHandler.setNext(loggingHandler);

        PaymentContext context = new PaymentContext(request);
        validationHandler.handle(context);

        return context.getPaymentResult();
    }
}