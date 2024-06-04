package com.gobus.gobusservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int paymentId;
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    @Column(name = "price")
    private double price;
    @Column(name = "payment_time")
    private ZonedDateTime paymentTime;

    public enum PaymentMode {
        CARD, UPI, BANK
    }

    public enum PaymentStatus {
        SUCCESS, FAILED;
    }
}
