package com.gobus.gobusservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private UserDto user;
    private String token;
    private String error;
}
