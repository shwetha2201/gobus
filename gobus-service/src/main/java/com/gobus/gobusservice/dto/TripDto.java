package com.gobus.gobusservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class TripDto {

    private int tripId;
    @NotNull
    private BusDto bus;
    @NotNull
    @Size(min = 4, max = 15)
    private String from;
    @NotNull
    @Size(min = 4, max = 15)
    private String to;
    private int availableSeats;
    @NotNull
    @Future
    private LocalDateTime boardingTime;
    @NotNull
    @Future
    private LocalDateTime arrivalTime;
    @NotNull
    @Size(min = 10, max = 12)
    private String driverPhoneNumber;
    @NotNull
    private String driverName;
    @NotNull
    private boolean confirmationStatus;
    @NotNull
    @Min(1)
    private double seatPrice;
}
