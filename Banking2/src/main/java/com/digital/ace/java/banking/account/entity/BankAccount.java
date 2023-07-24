package com.digital.ace.java.banking.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bank_Accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
