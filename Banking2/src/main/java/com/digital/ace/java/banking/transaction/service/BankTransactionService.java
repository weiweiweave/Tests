package com.digital.ace.java.banking.transaction.service;

import com.digital.ace.java.banking.transaction.entity.BankTransaction;

import java.util.List;
import java.util.Optional;

public interface BankTransactionService {

    public List<BankTransaction> findAll();

    BankTransaction save(BankTransaction bankTransaction);

    Optional<BankTransaction> find(Long id);
}
