package com.gobus.gobusservice.dto;

import com.gobus.gobusservice.model.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class PaymentDto {
    private int paymentId;
    @NotNull
    private Payment.PaymentMode paymentMode;
    @NotNull
    private Payment.PaymentStatus paymentStatus;
    @NotNull
    private double price;
    @NotNull
    private ZonedDateTime paymentTime;
}
