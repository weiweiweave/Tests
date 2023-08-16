package com.digital.ace.java.banking.role.mapper;

import com.digital.ace.java.banking.employee.dto.CreateEmployeeRequest;
import com.digital.ace.java.banking.employee.dto.EmployeeDTO;
import com.digital.ace.java.banking.employee.entity.Employee;
import com.digital.ace.java.banking.role.entity.Role;

import java.time.LocalDateTime;

public class RoleMapper {

//    public static EmployeeDTO toDTO(Employee employee) {
//        String username = employee.getUsername();
//        String email = employee.getEmailAddress();
//
//        return new EmployeeDTO(username,email);
//    }

    public static Role toRole(CreateEmployeeRequest createEmployeeRequest) {
        return new Role(createEmployeeRequest.getUsername(),createEmployeeRequest.getRole());
    }
}
