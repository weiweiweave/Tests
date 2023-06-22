package com.finance.bankingApp.controllers;

import com.finance.bankingApp.entities.Customer;
import com.finance.bankingApp.repositories.CustomerRepository;
import com.finance.bankingApp.restService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final String template = "Hello, %s!";

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/createCustomer")
    public CustomerService createCustomer(@RequestParam(value = "lastName") String lastName) {
        return new CustomerService(String.format(template, lastName));
    }

//    @GetMapping("/customers")
//    public ArrayList<CustomerService> listCustomers() {
//        ArrayList<CustomerService> customerServiceList = new ArrayList<CustomerService>();
//        CustomerService customerService = null;
//        for (Customer customer : repository.findAll()) {
//            customerService = new CustomerService(customer.getId(),customer.getLastName(),customer.getFirstName());
//            customerServiceList.add(customerService);
//        }
//        return customerServiceList;
//    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerService>> getAllCustomers(@RequestParam(required = false) String title) {
        try {
            List<CustomerService> customerServiceList = new ArrayList<CustomerService>();

            if (title == null) {
                //customers.addAll(customerRepository.findAll());
                CustomerService customerService = null;
                for (Customer customer : customerRepository.findAll()) {
                    customerService = new CustomerService(customer.getId(),customer.getLastName(),customer.getFirstName());
                    customerServiceList.add(customerService);
                }
            }
            //else
                //customerRepository.findByLastName(title).forEach(customers::add);

            if (customerServiceList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerServiceList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
