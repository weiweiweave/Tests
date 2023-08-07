package com.digital.ace.java.banking.customer.dao;

import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers c", nativeQuery = true)
    List<Customer> findAll();

    @Query(value = "SELECT * FROM customers c WHERE c.nric= ?1", nativeQuery = true)
    Optional<Customer> findByNRIC(String nric);

    @Query(value = "SELECT * FROM customers c WHERE c.uuid= ?1", nativeQuery = true)
    Optional<Customer> findByUUID(String uuid);
}
