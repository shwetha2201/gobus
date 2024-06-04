package com.gobus.gobusservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChangePasswordRequest {

    @NotEmpty
    private String currentPassword;
    @NotEmpty
    private String newPassword;
}
