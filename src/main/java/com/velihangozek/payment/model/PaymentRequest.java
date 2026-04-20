package com.velihangozek.payment.model;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        String currency,
        String paymentMethodType
) {
}