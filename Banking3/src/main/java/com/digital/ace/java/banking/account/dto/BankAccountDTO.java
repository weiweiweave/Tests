package com.digital.ace.java.banking.account.dto;

import com.digital.ace.java.banking.transaction.dto.BankTransactionDTO;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BankAccountDTO {

    private String accountNo;
    private String accountType;
    private String balance;
    private List<BankTransactionDTO> bankTransactionDTOList;

    public BankAccountDTO(String accountNo, String accountType, String balance, List<BankTransactionDTO> bankTransactionDTOList) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.bankTransactionDTOList = bankTransactionDTOList;
    }

    public BankAccountDTO(String accountNo, String accountType, String balance) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
    }

}
