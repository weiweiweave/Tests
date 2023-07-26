package com.digital.ace.java.banking.transaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bank_transactions")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_description")
    private String accountDescription;

    public BankTransaction(String accountDescription) {
        this.accountDescription = accountDescription;
    }
}
