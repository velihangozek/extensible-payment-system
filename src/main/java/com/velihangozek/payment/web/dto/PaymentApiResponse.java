package com.velihangozek.payment.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PaymentApiResponse(

        @Schema(description = "Indicates success", example = "true")
        boolean success,

        @Schema(description = "Result message", example = "Payment processed successfully")
        String message
) {}