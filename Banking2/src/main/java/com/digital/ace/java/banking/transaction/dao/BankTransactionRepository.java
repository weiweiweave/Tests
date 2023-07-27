package com.digital.ace.java.banking.transaction.dao;

import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>  {

    @Query(value = "SELECT * FROM bank_transactions t", nativeQuery = true)
    List<BankTransaction> findAll();
}
