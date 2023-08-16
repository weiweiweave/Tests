package com.digital.ace.java.banking.employee.service;

import com.digital.ace.java.banking.employee.dao.EmployeeRepository;
import com.digital.ace.java.banking.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> find(String username) {
        Optional<Employee> optionalUser = employeeRepository.findById(username);
        return optionalUser;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }
}
