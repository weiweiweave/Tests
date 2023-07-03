package com.finance.banking.customer.dao;

import com.finance.banking.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //List<Customer> findByLastName(String lastName);

    //Customer findById(Long id);

    @Query(value = "SELECT * FROM customers c", nativeQuery = true)
    List<Customer> findAll();
}
