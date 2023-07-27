package com.digital.ace.java.banking.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "fixedDeposit_Account")
public class FDAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long interestRate;

    private Integer depositPeriodInMonths;

    private LocalDate endDate;

    public FDAccount(Long interestRate, Integer depositPeriodInMonths, LocalDate endDate) {
        this.interestRate = interestRate;
        this.depositPeriodInMonths = depositPeriodInMonths;
        this.endDate = endDate;
    }

    public void depositMonthlyInterest (Long amount) {
        //
    }

    public void depositInterest (Long amount) {
        //
    }
}
