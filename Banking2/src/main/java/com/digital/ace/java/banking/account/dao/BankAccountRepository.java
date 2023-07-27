package com.digital.ace.java.banking.account.dao;

import com.digital.ace.java.banking.account.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>  {

    @Query(value = "SELECT * FROM bank_Accounts b", nativeQuery = true)
    List<BankAccount> findAll();
}
