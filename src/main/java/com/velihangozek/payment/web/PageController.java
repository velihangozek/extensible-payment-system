package com.velihangozek.payment.web;

import com.velihangozek.payment.enums.PaymentType;
import com.velihangozek.payment.model.PaymentRequest;
import com.velihangozek.payment.model.PaymentResult;
import com.velihangozek.payment.service.PaymentService;
import com.velihangozek.payment.web.dto.PaymentApiRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    private final PaymentService paymentService;

    public PageController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String paymentForm(Model model) {
        model.addAttribute("paymentRequest", new PaymentApiRequest(null, "USD", "CREDIT_CARD"));
        model.addAttribute("paymentTypes", PaymentType.values());
        return "payment-form";
    }

    @PostMapping("/pay")
    public String processPayment(@RequestParam String amount,
                                 @RequestParam String currency,
                                 @RequestParam String paymentMethodType,
                                 Model model) {
        PaymentRequest request = new PaymentRequest(
                new java.math.BigDecimal(amount),
                currency,
                PaymentType.valueOf(paymentMethodType.toUpperCase())
        );

        PaymentResult result = paymentService.processPayment(request);

        model.addAttribute("result", result);
        model.addAttribute("paymentTypes", PaymentType.values());
        return "payment-result";
    }
}