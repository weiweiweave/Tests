package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.account.dao.AccountTypeRepository;
import com.digital.ace.java.banking.account.dto.CreateAccountTypeDTO;
import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.user.dao.UserRepository;
import com.digital.ace.java.banking.user.dto.CreateUserDTO;
import com.digital.ace.java.banking.user.entity.User;
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

@Order(value=3)
@Component
public class AccountTypeBootStrapData implements CommandLineRunner {

    private AccountTypeRepository accountTypeRepository;

    //inject properties for sample.accountType
    @Value("${sample.accountType}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(AccountTypeBootStrapData.class);

    public AccountTypeBootStrapData(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {

            List<CreateAccountTypeDTO> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(CreateAccountTypeDTO.class).build().parse();

            Iterator<CreateAccountTypeDTO> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CreateAccountTypeDTO csvAccountType = csvUserIterator.next();

                AccountType newAccountType = new AccountType(
                        csvAccountType.getAccountDescription());

                accountTypeRepository.save(newAccountType);
            }

    }
}