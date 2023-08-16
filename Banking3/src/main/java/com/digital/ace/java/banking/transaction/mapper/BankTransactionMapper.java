package com.digital.ace.java.banking.transaction.mapper;

import com.digital.ace.java.banking.transaction.dto.BankTransactionDTO;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;

public class BankTransactionMapper {

    public static BankTransactionDTO toDTO(BankTransaction bankTransaction) {
        String accountNo = bankTransaction.getAccountNo();
        String amount = String.valueOf(bankTransaction.getAmount());
        String isCredit = String.valueOf(bankTransaction.getIsCredit());
        String remarks = bankTransaction.getRemarks();

        return new BankTransactionDTO(accountNo,amount,isCredit,remarks);
    }

//    public static Employee toUser(CreateEmployeeRequest createUserRequest) {
//        return new Employee(createUserRequest.getUsername(), createUserRequest.getPassword(), createUserRequest.getEmail(), LocalDateTime.now());
//    }
}
