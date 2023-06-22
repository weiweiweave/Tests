package com.finance.bankingApp.repositories;

import com.finance.bankingApp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    //Customer findById(Long id);

    @Query(value = "SELECT * FROM customers c", nativeQuery = true)
    List<Customer> findAll();
}
