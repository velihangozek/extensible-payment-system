package com.velihangozek.payment.chain;

import com.velihangozek.payment.model.PaymentContext;
import com.velihangozek.payment.model.PaymentResult;

public class PaymentExecutionHandler extends AbstractPaymentHandler {

    @Override
    public void handle(PaymentContext context) {
        PaymentResult result = context.getPaymentProcessor().process(context.getRequest());
        context.setPaymentResult(result);

        handleNext(context);
    }
}