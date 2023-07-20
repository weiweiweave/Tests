package com.digital.ace.java.banking.customer.rest;

import com.digital.ace.java.banking.customer.dto.CustomerDTO;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.customer.mapper.CustomerMapper;
import com.digital.ace.java.banking.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService theCustomerService) {
        customerService = theCustomerService;
    }


    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        List<Customer> customerList = customerService.findAll();
        //logger.trace(customerList.toString());
        List<CustomerDTO> customerDTOList = customerList.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        //logger.trace(customerDTOList.toString());
        return customerDTOList;
    }
}
