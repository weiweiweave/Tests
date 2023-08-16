package com.digital.ace.java.banking.customer.service;

import com.digital.ace.java.banking.customer.dao.CustomerRepository;
import com.digital.ace.java.banking.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Customer save(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Optional<Customer> find(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer;
    }

    @Override
    public Optional<Customer> findByNRIC(String nric) {
        Optional<Customer> optionalCustomer = customerRepository.findByNRIC(nric);
        return optionalCustomer;
    }

    @Override
    public Optional<Customer> findByUUID(String uuid) {
        Optional<Customer> optionalCustomer = customerRepository.findByUUID(uuid);
        return optionalCustomer;
    }


}
