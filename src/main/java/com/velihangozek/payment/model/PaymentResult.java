package com.velihangozek.payment.model;

public record PaymentResult(
        boolean success,
        String message
) {
}