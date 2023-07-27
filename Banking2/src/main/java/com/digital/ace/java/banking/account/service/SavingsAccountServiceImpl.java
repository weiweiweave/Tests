package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.dao.BankAccountRepository;
import com.digital.ace.java.banking.account.dao.SavingsAccountRepository;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.entity.SavingsAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    public SavingsAccountServiceImpl(SavingsAccountRepository savingsAccountRepository) {
        this.savingsAccountRepository = savingsAccountRepository;
    }

    @Override
    public List<SavingsAccount> findAll() {
        return savingsAccountRepository.findAll();
    }

    @Override
    public SavingsAccount save(SavingsAccount savingsAccount) {
        return savingsAccountRepository.saveAndFlush(savingsAccount);
    }

    @Override
    public Optional<SavingsAccount> find(Long id) {
        Optional<SavingsAccount> optionalSavingsAccount = savingsAccountRepository.findById(id);
        return optionalSavingsAccount;
    }
}
