package com.digital.ace.java.banking.role.dao;

import com.digital.ace.java.banking.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM bank_Roles r", nativeQuery = true)
    List<Role> findAll();

    List<Role> findByUsername(String username);
}
