package com.digital.ace.java.banking.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "saving_Accounts")
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interest_rate")
    private Double annualInterestRate;

    @Column(name = "min_amount_to_cal_interest")
    private Double minAmountToCalInterest;

    public SavingsAccount(Double annualInterestRate, Double minAmountToCalInterest) {
        this.annualInterestRate = annualInterestRate;
        this.minAmountToCalInterest = minAmountToCalInterest;
    }

    public void depositMonthlyInterest (Long amount) {
        //
    }
}
