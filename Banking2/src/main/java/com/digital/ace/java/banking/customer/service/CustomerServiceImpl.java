package com.digital.ace.java.banking.customer.service;

import com.digital.ace.java.banking.customer.dao.CustomerRepository;
import com.digital.ace.java.banking.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
