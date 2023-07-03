package com.finance.banking.customer.service;

import com.finance.banking.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    Customer save(Customer customer);
}
