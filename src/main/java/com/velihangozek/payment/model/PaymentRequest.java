package com.velihangozek.payment.model;

import com.velihangozek.payment.enums.PaymentType;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        String currency,
        PaymentType paymentMethodType
) {
}