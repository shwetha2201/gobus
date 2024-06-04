package com.gobus.gobusservice.dto;

import com.gobus.gobusservice.model.Bus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BusDto {

    private int busId;
    @NotNull
    private UserDto busOwner;
    @NotNull
    private String busName;
    @NotNull
    private Bus.BusType busType;
    @NotNull
    private String busNumber;
    private int numberOfSeats;
}
