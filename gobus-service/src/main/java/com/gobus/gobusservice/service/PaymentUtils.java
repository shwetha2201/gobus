package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.PaymentDto;
import com.gobus.gobusservice.model.Payment;

public class PaymentUtils {

    public static Payment dtoToEntity(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setPaymentMode(paymentDto.getPaymentMode());
        payment.setPrice(payment.getPrice());
        return payment;
    }

    public static PaymentDto entityToDto(Payment payment){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(payment.getPaymentId());
        paymentDto.setPaymentMode(payment.getPaymentMode());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setPaymentTime(payment.getPaymentTime());
        paymentDto.setPrice(payment.getPrice());
        return paymentDto;
    }
}
