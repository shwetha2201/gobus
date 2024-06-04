package com.gobus.gobusservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id")
    private int tripId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bus_id", referencedColumnName = "bus_id")
    private Bus bus;
    @Column(name = "from_place")
    private String from;
    @Column(name = "to_place")
    private String to;
    @Column(name = "available_seats")
    private int availableSeats;
    @Column(name = "boarding_time")
    private LocalDateTime boardingTime;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @Column(name = "driver_phone_number")
    private String driverPhoneNumber;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "confirmation_status")
    private boolean confirmationStatus;
    @Column(name = "seat_price")
    private double seatPrice;
}
