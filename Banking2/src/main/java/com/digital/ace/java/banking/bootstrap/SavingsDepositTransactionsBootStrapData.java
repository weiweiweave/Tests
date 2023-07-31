package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.service.BankAccountService;
import com.digital.ace.java.banking.exception.InsufficientBalanceException;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import com.digital.ace.java.banking.transaction.dto.CreateSavingDepositTransactionDTO;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import com.digital.ace.java.banking.transaction.service.BankTransactionService;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Order(value=5)
@Component
public class SavingsDepositTransactionsBootStrapData implements CommandLineRunner {

    private BankTransactionService bankTransactionService;

    private BankAccountService bankAccountService;

    //inject properties for sample.savingDepositTransactions
    @Value("${sample.savingDepositTransactions}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(SavingsDepositTransactionsBootStrapData.class);

    public SavingsDepositTransactionsBootStrapData(BankTransactionService bankTransactionService, BankAccountService bankAccountService) {
        this.bankTransactionService = bankTransactionService;
        this.bankAccountService = bankAccountService;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {

            List<CreateSavingDepositTransactionDTO> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(CreateSavingDepositTransactionDTO.class).build().parse();

            Iterator<CreateSavingDepositTransactionDTO> csvSavingDepositTransactionIterator = csvToBean.iterator();

            while (csvSavingDepositTransactionIterator.hasNext()) {
                CreateSavingDepositTransactionDTO csvCreateSavingDepositTransaction = csvSavingDepositTransactionIterator.next();

                Double csvAmount= Double.parseDouble(csvCreateSavingDepositTransaction.getAmount());
                Boolean csvIsCredit = Boolean.valueOf(csvCreateSavingDepositTransaction.getIsCredit());
                BankAccount csvBankAccount;

                Optional<BankAccount> optionalBankAccount = bankAccountService.findByAccountNo(csvCreateSavingDepositTransaction.getAccountNo());

                if (optionalBankAccount.isPresent()) {
                    csvBankAccount = optionalBankAccount.get();

                    BankTransaction newBankTransaction = new BankTransaction(
                            csvCreateSavingDepositTransaction.getUuid(),
                            csvCreateSavingDepositTransaction.getStaffIdWhoKeyIn(),
                            csvCreateSavingDepositTransaction.getAccountNo(),
                            csvAmount,
                            csvIsCredit,
                            csvCreateSavingDepositTransaction.getRemarks(),
                            LocalDateTime.now(),
                            csvBankAccount);

                    if (csvIsCredit) {
                        bankTransactionService.save(newBankTransaction);
                        // to deposit to account
                        bankAccountService.deposit(csvCreateSavingDepositTransaction.getAccountNo(),csvAmount);
                    }
                    else {
                        // to withdraw from account
                        Double currentBalance = csvBankAccount.getBalance();
                        if (currentBalance>=csvAmount) {
                            bankTransactionService.save(newBankTransaction);
                            bankAccountService.withdrawal(csvCreateSavingDepositTransaction.getAccountNo(),csvAmount);
                        }
                        else {
                            throw new InsufficientBalanceException(csvCreateSavingDepositTransaction.getAccountNo());
                        }
                    }
                }
                else {
                    throw new ItemNotFoundException(csvCreateSavingDepositTransaction.getAccountNo());
                }
            }

    }
}