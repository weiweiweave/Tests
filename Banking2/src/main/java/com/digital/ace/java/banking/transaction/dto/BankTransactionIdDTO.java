package com.digital.ace.java.banking.transaction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankTransactionIdDTO {
    private Long id;
    public BankTransactionIdDTO(Long id) {
        this.id = id;
    }
}
