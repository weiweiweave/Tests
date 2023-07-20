package com.digital.ace.java.banking.user.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    @CsvBindByName(column = "Username")
    private String username;

    @CsvBindByName(column = "Password")
    private String password;

    @CsvBindByName(column = "Email")
    private String emailAddress;

    @CsvBindByName(column = "Roles")
    private String roles;
}
