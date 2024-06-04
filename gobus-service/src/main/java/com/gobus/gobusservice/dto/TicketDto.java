package com.gobus.gobusservice.dto;

import com.gobus.gobusservice.model.Ticket;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TicketDto {

    private int ticketId;

    @NotNull
    private UserDto user;
    @NotNull
    private TripDto trip;
    @NotNull
    private PaymentDto payment;
    @NotNull
    private Ticket.TicketStatus ticketStatus;
    @NotNull
    private String passengerName;
    @NotNull
    @Size(min = 10, max = 12)
    private String passengerPhoneNumber;
    @NotNull
    private int numberOfSeatsBooked;
    @NotNull
    private double totalPrice;
}
