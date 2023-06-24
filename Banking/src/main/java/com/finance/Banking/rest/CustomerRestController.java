package com.finance.Banking.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.finance.Banking.entity.Customer;
import com.finance.Banking.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private static final String template = "Hello, %s!";

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
//    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String title) {
//        try {
//            List<Customer> customerList = new List<Customer>();
//
//            if (title == null) {
//                //for (Customer customer : customerService.findAll()) {
//                    //customerService = new CustomerService(customer.getId(),customer.getLastName(),customer.getFirstName());
//                    //customerServiceList.add(customerService);
//                //}
//                customerList = customerService.findAll();
//            }
//            //else
//                //customerRepository.findByLastName(title).forEach(customers::add);
//
//            if (customerList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(customerList, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    public List<Customer> findAll() {
        return customerService.findAll();
    }
}
