package com.digital.ace.java.banking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    @NotBlank(message = "Invalid Username: Empty Username")
    @NotNull(message = "Invalid Username: Username is NULL")
    @Size(min = 3, max = 30, message = "Invalid Username: Must be of 3 - 30 characters")
    private String username;

    @NotBlank(message = "Invalid Password: Empty password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @Size(min = 8, max = 30, message = "Invalid Password: Must be of 8 - 30 characters")
    private String password;

    @Email(message = "Invalid email")
    private String email;
}
