package com.digital.ace.java.banking.customer.service;

import com.digital.ace.java.banking.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> find(Long id);

    Optional<Customer> findByNRIC(String nric);

    Optional<Customer> findByUUID(String uuid);
}
