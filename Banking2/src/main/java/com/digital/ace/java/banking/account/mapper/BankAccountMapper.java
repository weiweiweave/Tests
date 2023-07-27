package com.digital.ace.java.banking.account.mapper;

import com.digital.ace.java.banking.BankingApplication;
import com.digital.ace.java.banking.account.dao.AccountTypeRepository;
import com.digital.ace.java.banking.account.dto.BankAccountDTO;
import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.service.AccountTypeService;
import com.digital.ace.java.banking.exception.IDNotFoundException;
import com.digital.ace.java.banking.user.dto.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class BankAccountMapper {

    //private static AccountTypeService accountTypeService = BankingApplication.getAppContext().getBean(AccountTypeService.class);
    private static AccountTypeService accountTypeService;

    @Autowired
    public BankAccountMapper(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    public static BankAccountDTO toDTO(BankAccount bankAccount) {



        String accountNo = bankAccount.getAccountNo();
        String accountTypeStr = "";
        Optional<AccountType> optionalAccountType = accountTypeService.find(bankAccount.getAccountType());
        AccountType accountType;

        if (optionalAccountType.isPresent()) {
            accountType = optionalAccountType.get();
            accountTypeStr = accountType.getAccountDescription();
        }
        else {
            throw new IDNotFoundException(bankAccount.getAccountType());
        }
        String balance = bankAccount.getBalance().toString();

        return new BankAccountDTO(accountNo,accountTypeStr,balance);
    }

//    public static BankAccount toBankAccount(CreateUserRequest createUserRequest) {
//        return new User(createUserRequest.getUsername(), createUserRequest.getPassword(), createUserRequest.getEmail(), LocalDateTime.now());
//    }
}
