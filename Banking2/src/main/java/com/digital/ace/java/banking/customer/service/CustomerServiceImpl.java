package com.digital.ace.java.banking.customer.service;

import com.digital.ace.java.banking.customer.dao.CustomerRepository;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
        customerRepository = theCustomerRepository;
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
