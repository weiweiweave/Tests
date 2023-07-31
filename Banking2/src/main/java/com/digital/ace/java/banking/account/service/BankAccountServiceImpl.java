package com.digital.ace.java.banking.account.service;

import com.digital.ace.java.banking.account.dao.BankAccountRepository;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.exception.InsufficientBalanceException;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
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

    @Override
    public void deposit(String accountNo, Double amount) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByAccountNo(accountNo);

        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            Double currentBalance = bankAccount.getBalance();
            currentBalance += amount;
            bankAccount.setBalance(currentBalance);
            bankAccountRepository.saveAndFlush(bankAccount);
        }
        else {
            throw new ItemNotFoundException(accountNo);
        }
    }

    @Override
    public void withdrawal(String accountNo, Double amount) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByAccountNo(accountNo);

        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            Double currentBalance = bankAccount.getBalance();
            if (currentBalance>=amount) {
                currentBalance -= amount;
                bankAccount.setBalance(currentBalance);
                bankAccountRepository.saveAndFlush(bankAccount);
            }
            else {
                throw new InsufficientBalanceException(accountNo);
            }
        }
        else {
            throw new ItemNotFoundException(accountNo);
        }
    }
}
