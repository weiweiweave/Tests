package com.finance.banking.bankAccount.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BankAccount {
    private Long customerID;

    private Long accountID;

    private Double balance;

    private LocalDateTime creationDateTime;

    public void deposit (Long amount) {
        //
    }

    public void withdrawal (Long amount) {
        //
    }
}
