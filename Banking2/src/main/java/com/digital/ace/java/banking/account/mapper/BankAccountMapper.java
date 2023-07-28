package com.digital.ace.java.banking.account.mapper;

import com.digital.ace.java.banking.BankingApplication;
import com.digital.ace.java.banking.account.dao.AccountTypeRepository;
import com.digital.ace.java.banking.account.dao.SavingsAccountRepository;
import com.digital.ace.java.banking.account.dto.BankAccountDTO;
import com.digital.ace.java.banking.account.dto.CreateBankAccountRequest;
import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.entity.SavingsAccount;
import com.digital.ace.java.banking.account.service.AccountTypeService;
import com.digital.ace.java.banking.account.service.BankAccountService;
import com.digital.ace.java.banking.account.service.SavingsAccountService;
import com.digital.ace.java.banking.exception.IDNotFoundException;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import com.digital.ace.java.banking.transaction.dto.BankTransactionDTO;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import com.digital.ace.java.banking.user.dto.CreateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BankAccountMapper {

    //private static AccountTypeService accountTypeService = BankingApplication.getAppContext().getBean(AccountTypeService.class);
    private static AccountTypeService accountTypeService;

    private static SavingsAccountService savingsAccountService;

    @Autowired
    public BankAccountMapper(AccountTypeService accountTypeService, SavingsAccountService savingsAccountService) {
        this.accountTypeService = accountTypeService;
        this.savingsAccountService = savingsAccountService;
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

        List<BankTransaction> bankTransactionList = bankAccount.getBankTransaction();
        List<BankTransactionDTO> bankTransactionDTOList = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactionList) {
            BankTransactionDTO bankTransactionDTO = new BankTransactionDTO();
            bankTransactionDTO.setAccountNo(bankTransaction.getAccountNo());
            bankTransactionDTO.setAmount(bankTransaction.getAmount().toString());
            bankTransactionDTO.setIsCredit(bankTransaction.getIsCredit().toString());
            bankTransactionDTO.setRemarks(bankTransaction.getRemarks());
            bankTransactionDTOList.add(bankTransactionDTO);
        }

        //System.out.println(bankTransactionList);

        return new BankAccountDTO(accountNo,accountTypeStr,balance,bankTransactionDTOList);
    }

    public static BankAccount toBankAccount(CreateBankAccountRequest createBankAccountRequest) {
        LocalDate createdDate = LocalDate.parse(createBankAccountRequest.getCreatedDate());
        Double balance = Double.parseDouble(createBankAccountRequest.getBalance());
        Optional<AccountType> optionalAccountType = accountTypeService.findByAccountDescription(createBankAccountRequest.getAccountType());
        AccountType accountType;
        Long accountTypeInt = Long.valueOf(0);

        if (optionalAccountType.isPresent()) {
            accountType = optionalAccountType.get();
            accountTypeInt = accountType.getId();
        }
        else {
            throw new ItemNotFoundException(createBankAccountRequest.getAccountType());
        }

        Double interestRate = Double.parseDouble(createBankAccountRequest.getInterestRate());
        Double minAmountToCalInterest = Double.parseDouble(createBankAccountRequest.getMinAmountToCalInterest());

        SavingsAccount newSavingsAccount = new SavingsAccount(
                interestRate,
                minAmountToCalInterest);

        //logger.trace(newSavingsAccount.toString());

        SavingsAccount savedSavingsAccount = savingsAccountService.save(newSavingsAccount);

        return new BankAccount(
                createBankAccountRequest.getUuid(),
                createBankAccountRequest.getStaffIdWhoKeyIn(),
                createdDate,
                createBankAccountRequest.getCustomerNric(),
                balance,
                createBankAccountRequest.getAccountNo(),
                accountTypeInt,
                savedSavingsAccount.getId(),
                LocalDateTime.now());
    }
}
