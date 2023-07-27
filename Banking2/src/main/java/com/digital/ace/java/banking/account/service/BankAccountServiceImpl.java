package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.dao.BankAccountRepository;
import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository theBankAccountRepository) {
        bankAccountRepository = theBankAccountRepository;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.saveAndFlush(bankAccount);
    }

    @Override
    public Optional<BankAccount> find(Long id) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);
        return optionalBankAccount;
    }

    @Override
    public Optional<BankAccount> findByAccountNo(String accountNo) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByAccountNo(accountNo);
        return optionalBankAccount;
    }
}
