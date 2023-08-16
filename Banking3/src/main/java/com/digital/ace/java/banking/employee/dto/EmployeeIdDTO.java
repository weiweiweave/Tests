package com.digital.ace.java.banking.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeIdDTO {
    private String username;
    public EmployeeIdDTO(String username) {
        this.username = username;
    }
}
