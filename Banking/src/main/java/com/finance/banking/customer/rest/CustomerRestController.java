package com.finance.banking.customer.rest;


import com.finance.banking.customer.dto.CustomerDTO;
import com.finance.banking.customer.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.banking.customer.entity.Customer;
import com.finance.banking.customer.service.CustomerService;


@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private static final String template = "Hello, %s!";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService theCustomerService) {
        customerService = theCustomerService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

//    @GetMapping("/createCustomer")
//    public CustomerService createCustomer(@RequestParam(value = "lastName") String lastName) {
//        return new CustomerService(String.format(template, lastName));
//    }


    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        List<Customer> customerList = customerService.findAll();
        //logger.trace(customerList.toString());
        List<CustomerDTO> customerDTOList = customerList.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        //logger.trace(customerDTOList.toString());
        return customerDTOList;
    }
}
