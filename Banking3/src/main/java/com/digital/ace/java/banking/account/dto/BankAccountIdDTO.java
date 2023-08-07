package com.digital.ace.java.banking.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountIdDTO {
    private Long id;
    public BankAccountIdDTO(Long id) {
        this.id = id;
    }
}
