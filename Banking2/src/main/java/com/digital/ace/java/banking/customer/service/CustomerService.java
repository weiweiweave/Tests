package com.digital.ace.java.banking.customer.service;

import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> find(Long id);
}
