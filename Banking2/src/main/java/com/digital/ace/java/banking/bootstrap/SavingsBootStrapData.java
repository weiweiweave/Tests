package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.account.dao.BankAccountRepository;
import com.digital.ace.java.banking.account.dao.SavingsAccountRepository;
import com.digital.ace.java.banking.account.dto.CreateBankAccountDTO;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.entity.SavingsAccount;
import com.digital.ace.java.banking.account.service.BankAccountService;
import com.digital.ace.java.banking.account.service.SavingsAccountService;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Order(value=4)
@Component
public class SavingsBootStrapData implements CommandLineRunner  {

    private BankAccountService bankAccountService;

    private SavingsAccountService savingsAccountService;

    //inject properties for sample.savingDeposit
    @Value("${sample.savingDeposit}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(SavingsBootStrapData.class);

    public SavingsBootStrapData(BankAccountService bankAccountService, SavingsAccountService savingsAccountService) {
        this.bankAccountService = bankAccountService;
        this.savingsAccountService = savingsAccountService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<CreateBankAccountDTO> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(CreateBankAccountDTO.class).build().parse();

        Iterator<CreateBankAccountDTO> csvBankAccountIterator = csvToBean.iterator();

        while (csvBankAccountIterator.hasNext()) {
            CreateBankAccountDTO csvBankAccount = csvBankAccountIterator.next();

            //logger.trace(csvBankAccount.toString());

            LocalDate csvCreatedDate = LocalDate.parse(csvBankAccount.getCreatedDate());
            Double csvInterestRate = Double.parseDouble(csvBankAccount.getInterestRate());
            Double csvMinAmountToCalInterest = Double.parseDouble(csvBankAccount.getMinAmountToCalInterest());

            SavingsAccount newSavingsAccount = new SavingsAccount(
                    csvInterestRate,
                    csvMinAmountToCalInterest);

            //logger.trace(newSavingsAccount.toString());

            SavingsAccount savedSavingsAccount = savingsAccountService.save(newSavingsAccount);

            //logger.trace(savedSavingsAccount.toString());

            BankAccount newBankAccount = new BankAccount(
                    csvBankAccount.getUuid(),
                    csvBankAccount.getStaffIdWhoKeyIn(),
                    csvCreatedDate,
                    csvBankAccount.getCustomerNric(),
                    Double. parseDouble(csvBankAccount.getBalance()),
                    csvBankAccount.getAccountNo(),
                    Long.valueOf(1),
                    savedSavingsAccount.getId(),
                    LocalDateTime.now());

            //logger.trace(newBankAccount.toString());

            bankAccountService.save(newBankAccount);
        }
    }
}
