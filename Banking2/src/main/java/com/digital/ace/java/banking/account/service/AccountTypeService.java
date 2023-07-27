package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {

    public List<AccountType> findAll();

    AccountType save(AccountType accountType);

    Optional<AccountType> find(Long id);

    Optional<AccountType> findByAccountDescription(String description);
}
