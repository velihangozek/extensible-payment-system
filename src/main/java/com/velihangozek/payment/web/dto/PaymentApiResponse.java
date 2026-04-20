package com.velihangozek.payment.web.dto;

public record PaymentApiResponse(
        boolean success,
        String message
) {
}