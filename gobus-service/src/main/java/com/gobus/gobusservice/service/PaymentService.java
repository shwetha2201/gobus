package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.PaymentDto;
import com.gobus.gobusservice.model.Payment;
import com.gobus.gobusservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepo;

    public PaymentDto pay(PaymentDto paymentDto) {
        try {
            Payment pay = PaymentUtils.dtoToEntity(paymentDto);
            Payment paymentDb = paymentRepo.save(pay);
            return PaymentUtils.entityToDto(paymentDb);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to Add Payment Details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!! Please try later.");
        }
    }

    public PaymentDto getPayment(int id) {
        try {
            Optional<Payment> payment = paymentRepo.findById(id);
            log.info("checking for payment details in database for given id...");
            if (payment.isEmpty()) {
                log.error("payment not found for given id...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found for given Id...!");
            }
            log.info("Returning the Payment Details {} ", payment.get());
            return PaymentUtils.entityToDto(payment.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve payment details by id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }
}
