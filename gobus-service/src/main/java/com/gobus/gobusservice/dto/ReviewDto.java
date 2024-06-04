package com.gobus.gobusservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewDto {

    private Integer reviewId;
    @NotNull
    private UserDto user;
    @NotNull
    private BusDto bus;
    @NotNull
    private String comment;
    @NotNull
    private Integer numberOfStars;
}
