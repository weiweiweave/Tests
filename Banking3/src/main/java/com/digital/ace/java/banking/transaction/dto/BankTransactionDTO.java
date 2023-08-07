package com.digital.ace.java.banking.transaction.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankTransactionDTO {

    private String accountNo;
    private String amount;
    private String isCredit;
    private String remarks;

    public BankTransactionDTO(String accountNo, String amount, String isCredit, String remarks) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.isCredit = isCredit;
        this.remarks = remarks;
    }
}
