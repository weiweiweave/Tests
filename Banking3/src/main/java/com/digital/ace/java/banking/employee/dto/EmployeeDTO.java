package com.digital.ace.java.banking.employee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {

    private String username;
    private String email;

    public EmployeeDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
