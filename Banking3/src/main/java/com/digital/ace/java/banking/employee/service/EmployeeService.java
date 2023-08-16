package com.digital.ace.java.banking.employee.service;

import com.digital.ace.java.banking.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> findAll();

    Employee save(Employee employee);

    Optional<Employee> find(String username);
}
