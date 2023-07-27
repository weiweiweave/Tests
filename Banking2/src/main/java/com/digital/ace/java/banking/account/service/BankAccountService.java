package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {

    public List<BankAccount> findAll();

    BankAccount save(BankAccount bankAccount);

    Optional<BankAccount> find(Long id);

    Optional<BankAccount> findByAccountNo(String accountNo);
}
