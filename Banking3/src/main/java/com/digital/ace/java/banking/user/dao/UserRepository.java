package com.digital.ace.java.banking.user.dao;

import com.digital.ace.java.banking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //List<Customer> findByLastName(String lastName);

    //Customer findById(Long id);

    @Query(value = "SELECT * FROM bank_Users u", nativeQuery = true)
    List<User> findAll();
}
