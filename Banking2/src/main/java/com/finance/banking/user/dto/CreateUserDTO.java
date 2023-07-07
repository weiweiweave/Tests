package com.finance.banking.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String username;
    private String password;
    private String email;
}
