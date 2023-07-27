package com.digital.ace.java.banking.transaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bank_transactions")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_id_who_key_in")
    private String staffIdWhoKeyIn;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "is_credit")
    private Boolean isCredit;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    public BankTransaction(String staffIdWhoKeyIn, String accountNo, Double amount, Boolean isCredit, String remarks, LocalDateTime creationDateTime) {
        this.staffIdWhoKeyIn = staffIdWhoKeyIn;
        this.accountNo = accountNo;
        this.amount = amount;
        this.isCredit = isCredit;
        this.remarks = remarks;
        this.creationDateTime = creationDateTime;
    }

}
