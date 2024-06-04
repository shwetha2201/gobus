package com.gobus.gobusservice.dto;

import com.gobus.gobusservice.model.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private Integer userId;
    @NotNull
    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-z A-Z]+")
    private String fullName;

    @Min(18)
    @Max(80)
    private Integer age;

    private User.UserType userType;

    @Email
    private String emailId;

    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    private String password;

}
