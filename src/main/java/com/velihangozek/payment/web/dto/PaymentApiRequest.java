package com.velihangozek.payment.web.dto;

import java.math.BigDecimal;

public record PaymentApiRequest(
        BigDecimal amount,
        String currency,
        String paymentMethodType
) {
}