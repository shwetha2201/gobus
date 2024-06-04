package com.gobus.gobusservice.repository;

import com.gobus.gobusservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
