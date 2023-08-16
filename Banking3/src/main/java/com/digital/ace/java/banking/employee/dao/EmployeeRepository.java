package com.digital.ace.java.banking.employee.dao;

import com.digital.ace.java.banking.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    //derived method name query in spring data jpa i.e. query to be executed in database
    //server derived based on method name defined in repository interface
    //List<Customer> findByLastName(String lastName);

    @Query(value = "SELECT * FROM bank_Employees e", nativeQuery = true)
    List<Employee> findAll();

//    @Query(value = "SELECT * FROM bank_Users u WHERE u.username= ?1", nativeQuery = true)
//    Optional<Employee> findByUsername(String username);

    Optional<Employee> findById(String username);

    List<Employee> findByUsername(String username);
}
