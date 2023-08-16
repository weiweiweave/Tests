package com.digital.ace.java.banking.employee.rest;


import com.digital.ace.java.banking.employee.dto.CreateEmployeeRequest;
import com.digital.ace.java.banking.employee.dto.EmployeeDTO;
import com.digital.ace.java.banking.employee.entity.Employee;
import com.digital.ace.java.banking.exception.ExceptionJSONInfo;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import com.digital.ace.java.banking.employee.dto.EmployeeIdDTO;
import com.digital.ace.java.banking.employee.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import com.digital.ace.java.banking.employee.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping("/user")
    public EmployeeIdDTO createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest)  throws Exception {
        Employee employee = EmployeeMapper.toUser(createEmployeeRequest);

        //logger.trace(employee.toString());

        Employee savedEmployee = employeeService.save(employee);

        //logger.trace(savedEmployee.getId().toString());

        return new EmployeeIdDTO(savedEmployee.getUsername());
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> listUsers() {
        List<Employee> employeeList = employeeService.findAll();
        //logger.trace(employeeList.toString());
        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
        //logger.trace(employeeDTOList.toString());
        return employeeDTOList;
    }

    @GetMapping("/user/{id}")
    public EmployeeDTO getUser(@PathVariable("id") String username) throws ItemNotFoundException {

        Optional<Employee> optionalUser = employeeService.find(username);
        Employee employee;

        if(optionalUser.isPresent()) {
            employee = optionalUser.get();
        }
        else {
            throw new ItemNotFoundException("Username is not found - " + username);
        }
        return EmployeeMapper.toDTO(employee);
    }

    //Controller Based Exception Handling
    //to handle exceptions thrown by request handling (@RequestMapping)
    //methods in the same controller.
    //Handle exceptions without the @ResponseStatus annotation
    // (typically predefined exceptions that you didn't write)
    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody ExceptionJSONInfo handleNoSuchElementException(HttpServletRequest request, Exception ex) {

        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());

        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ExceptionJSONInfo handleValidationExceptions(
            HttpServletRequest request,
            MethodArgumentNotValidException ex) {

        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setMessage(errors.toString());

        return response;
    }
}
