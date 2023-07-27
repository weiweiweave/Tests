package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.entity.SavingsAccount;

import java.util.List;
import java.util.Optional;

public interface SavingsAccountService {

    public List<SavingsAccount> findAll();

    SavingsAccount save(SavingsAccount savingsAccount);

    Optional<SavingsAccount> find(Long id);
}
