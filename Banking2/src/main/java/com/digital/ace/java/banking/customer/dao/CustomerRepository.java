package com.digital.ace.java.banking.customer.dao;

import com.digital.ace.java.banking.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers c", nativeQuery = true)
    List<Customer> findAll();
}
