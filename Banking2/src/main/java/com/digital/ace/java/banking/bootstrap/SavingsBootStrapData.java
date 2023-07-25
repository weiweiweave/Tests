package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.account.dao.AccountRepository;
import com.digital.ace.java.banking.account.dto.CreateBankAccountDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

@Order(value=3)
@Component
public class SavingsBootStrapData implements CommandLineRunner  {

    private AccountRepository accountRepository;

    //inject properties for sample.savingDeposit
    @Value("${sample.savingDeposit}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(SavingsBootStrapData.class);

    public SavingsBootStrapData(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //List<CreateBankAccountDTO> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(CreateBankAccountDTO.class).build().parse();

        //Iterator<CreateBankAccountDTO> csvBankAccountIterator = csvToBean.iterator();
    }
}
