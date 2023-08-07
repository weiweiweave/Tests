package com.digital.ace.java.banking.transaction.service;

import com.digital.ace.java.banking.transaction.dao.BankTransactionRepository;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @Override
    public List<BankTransaction> findAll() {
        return bankTransactionRepository.findAll();
    }

    @Override
    public BankTransaction save(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
    }

    @Override
    public Optional<BankTransaction> find(Long id) {
        Optional<BankTransaction> optionalBankTransaction = bankTransactionRepository.findById(id);
        return optionalBankTransaction;
    }


}
