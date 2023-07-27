package com.digital.ace.java.banking.account.dao;

import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>  {

    @Query(value = "SELECT * FROM bank_Accounts b", nativeQuery = true)
    List<BankAccount> findAll();

    @Query(value = "SELECT * FROM bank_Accounts b WHERE b.account_no= ?1", nativeQuery = true)
    Optional<BankAccount> findByAccountNo(String accountNo);
}
