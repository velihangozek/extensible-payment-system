package com.velihangozek.payment.web;

import com.velihangozek.payment.enums.PaymentType;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;
import com.velihangozek.payment.service.PaymentService;
import com.velihangozek.payment.web.dto.PaymentApiRequest;
import com.velihangozek.payment.web.dto.PaymentApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Payment processing endpoints")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(
            summary = "Process a payment",
            description = "Processes a payment request using the selected payment method"
    )
    public ResponseEntity<PaymentApiResponse> processPayment(@RequestBody PaymentApiRequest request) {
        PaymentRequest paymentRequest = new PaymentRequest(
                request.amount(),
                request.currency(),
                PaymentType.valueOf(request.paymentMethodType().trim().toUpperCase())
        );

        PaymentResult result = paymentService.processPayment(paymentRequest);

        return ResponseEntity.ok(new PaymentApiResponse(result.success(), result.message()));
    }
}