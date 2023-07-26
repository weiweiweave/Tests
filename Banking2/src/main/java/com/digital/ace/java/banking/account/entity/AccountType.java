package com.digital.ace.java.banking.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "account_Type")
public class AccountType {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_description")
    private String accountDescription;

    public AccountType(String accountDescription) {
        this.accountDescription = accountDescription;
    }
}
