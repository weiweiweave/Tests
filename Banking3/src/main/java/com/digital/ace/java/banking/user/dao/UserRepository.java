package com.digital.ace.java.banking.user.dao;

import com.digital.ace.java.banking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //List<Customer> findByLastName(String lastName);

    @Query(value = "SELECT * FROM bank_Users u", nativeQuery = true)
    List<User> findAll();

//    @Query(value = "SELECT * FROM bank_Users u WHERE u.username= ?1", nativeQuery = true)
//    Optional<User> findByUsername(String username);

    Optional<User> findById(String username);
}
