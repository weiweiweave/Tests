package com.digital.ace.java.banking.account.entity;

import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "bank_Accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "staff_id_who_key_in")
    private String staffIdWhoKeyIn;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "customer_nric")
    private String customerNric;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "account_type")
    private Long accountType;

    @Column(name = "sub_account_type_id")
    private Long subAccountTypeId;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<BankTransaction> bankTransaction;

    public BankAccount(String uuid, String staffIdWhoKeyIn, LocalDate createdDate, String customerNric, Double balance, String accountNo, Long accountType, Long subAccountTypeId, LocalDateTime creationDateTime) {
        this.uuid = uuid;
        this.staffIdWhoKeyIn = staffIdWhoKeyIn;
        this.createdDate = createdDate;
        this.customerNric = customerNric;
        this.balance = balance;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.subAccountTypeId = subAccountTypeId;
        this.creationDateTime = creationDateTime;
    }

}
