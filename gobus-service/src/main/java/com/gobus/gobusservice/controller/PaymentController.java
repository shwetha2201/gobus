package com.gobus.gobusservice.controller;

import com.gobus.gobusservice.dto.PaymentDto;
import com.gobus.gobusservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<PaymentDto> pay(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.pay(paymentDto));
    }

    @GetMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable int id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

}
