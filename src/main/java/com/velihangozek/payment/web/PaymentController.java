package com.velihangozek.payment.web;

import com.velihangozek.payment.enums.PaymentType;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;
import com.velihangozek.payment.service.PaymentService;
import com.velihangozek.payment.web.dto.PaymentApiRequest;
import com.velihangozek.payment.web.dto.PaymentApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentApiResponse> processPayment(@RequestBody PaymentApiRequest request) {
        PaymentRequest paymentRequest = new PaymentRequest(
                request.amount(),
                request.currency(),
                PaymentType.valueOf(request.paymentMethodType().toUpperCase())
        );

        PaymentResult result = paymentService.processPayment(paymentRequest);

        return ResponseEntity.ok(new PaymentApiResponse(result.success(), result.message()));
    }
}