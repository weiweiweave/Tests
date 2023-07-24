package com.digital.ace.java.banking.account.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingsAccount extends BankAccount {

    private Long annualInterestRate;

    public void depositMonthlyInterest (Long amount) {
        //
    }
}
