package com.digital.ace.java.banking.bootstrap;

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

@Order(value=5)
@Component
public class SavingsDepositTransactionsBootStrapData implements CommandLineRunner {

    private BankTransactionService bankTransactionService;

    //inject properties for sample.savingDepositTransactions
    @Value("${sample.savingDepositTransactions}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(SavingsDepositTransactionsBootStrapData.class);

    public SavingsDepositTransactionsBootStrapData(BankTransactionService bankTransactionService) {
        this.bankTransactionService = bankTransactionService;
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

                BankTransaction newBankTransaction = new BankTransaction(
                        csvCreateSavingDepositTransaction.getUuid(),
                        csvCreateSavingDepositTransaction.getStaffIdWhoKeyIn(),
                        csvCreateSavingDepositTransaction.getAccountNo(),
                        csvAmount,
                        csvIsCredit,
                        csvCreateSavingDepositTransaction.getRemarks(),
                        LocalDateTime.now());

                bankTransactionService.save(newBankTransaction);
            }

    }
}