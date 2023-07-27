package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.entity.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {

    public List<AccountType> findAll();

    Optional<AccountType> find(Long id);

    Optional<AccountType> findByAccountDescription(String description);
}
