package com.gobus.gobusservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private int ticketId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private Payment payment;
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "passenger_phoneNumber")
    private String passengerPhoneNumber;
    @Column(name = "num_seats_booked")
    private int numberOfSeatsBooked;
    @Column(name = "total_price")
    private double totalPrice;

    public enum TicketStatus {
        PENDING, CONFIRMED, CANCELLED
    }
}
