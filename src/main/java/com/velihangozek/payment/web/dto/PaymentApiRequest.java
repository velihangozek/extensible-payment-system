package com.velihangozek.payment.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record PaymentApiRequest(

        @Schema(description = "Payment amount", example = "150.00")
        BigDecimal amount,

        @Schema(description = "Currency code", example = "USD")
        String currency,

        @Schema(
                description = "Payment method type",
                example = "APPLE_PAY",
                allowableValues = {"CREDIT_CARD", "PAYPAL", "APPLE_PAY"}
        )
        String paymentMethodType
) {}