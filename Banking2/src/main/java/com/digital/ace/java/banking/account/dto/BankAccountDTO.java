package com.digital.ace.java.banking.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountDTO {

    private String accountNo;
    private String accountType;
    private String balance;

    public BankAccountDTO(String accountNo, String accountType, String balance) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
    }

}
