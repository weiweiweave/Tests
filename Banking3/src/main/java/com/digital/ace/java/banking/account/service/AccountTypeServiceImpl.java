package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.dao.AccountTypeRepository;
import com.digital.ace.java.banking.account.entity.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository theAccountTypeRepository) {
        accountTypeRepository = theAccountTypeRepository;
    }

    @Override
    public List<AccountType> findAll() {
        return accountTypeRepository.findAll();
    }

    @Override
    public AccountType save(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public Optional<AccountType> find(Long id) {

        Optional<AccountType> optionalAccountType = accountTypeRepository.findById(id);
        return optionalAccountType;
    }

    @Override
    public Optional<AccountType> findByAccountDescription(String description) {
        Optional<AccountType> optionalAccountType = accountTypeRepository.findByAccountDescription(description);
        return optionalAccountType;
    }

}
