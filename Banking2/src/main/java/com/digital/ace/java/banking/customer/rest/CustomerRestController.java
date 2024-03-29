package com.digital.ace.java.banking.customer.rest;

import com.digital.ace.java.banking.customer.dto.CreateCustomerRequest;
import com.digital.ace.java.banking.customer.dto.CustomerDTO;
import com.digital.ace.java.banking.customer.dto.CustomerIdDTO;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.customer.mapper.CustomerMapper;
import com.digital.ace.java.banking.customer.service.CustomerService;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping("/customer")
    public CustomerIdDTO createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest)  throws Exception {
        Customer customer = CustomerMapper.toCustomer(createCustomerRequest);

        customer.setId(Long.valueOf(0));

        //logger.trace(user.toString());

        Customer savedCustomer = customerService.save(customer);

        //logger.trace(savedUser.getId().toString());

        return new CustomerIdDTO(savedCustomer.getId());
    }

    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        List<Customer> customerList = customerService.findAll();
        //logger.trace(customerList.toString());
        List<CustomerDTO> customerDTOList = customerList.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        //logger.trace(customerDTOList.toString());
        return customerDTOList;
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") Long id) throws ItemNotFoundException {

        Optional<Customer> optionalCustomer = customerService.find(id);
        Customer customer = new Customer();

        if(optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        }
        else {
            throw new ItemNotFoundException("Customer id not found - " + id);
        }
        return CustomerMapper.toDTO(customer);
    }

}
