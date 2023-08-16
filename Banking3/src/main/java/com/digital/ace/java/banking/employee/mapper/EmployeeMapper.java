package com.digital.ace.java.banking.employee.mapper;

import com.digital.ace.java.banking.employee.dto.CreateEmployeeRequest;
import com.digital.ace.java.banking.employee.dto.EmployeeDTO;
import com.digital.ace.java.banking.employee.entity.Employee;

import java.time.LocalDateTime;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        String username = employee.getUsername();
        String email = employee.getEmailAddress();

        return new EmployeeDTO(username,email);
    }

    public static Employee toUser(CreateEmployeeRequest createEmployeeRequest) {
        return new Employee(createEmployeeRequest.getUsername(), createEmployeeRequest.getPassword(), createEmployeeRequest.getEmail(), createEmployeeRequest.getActive(), LocalDateTime.now());
    }
}
