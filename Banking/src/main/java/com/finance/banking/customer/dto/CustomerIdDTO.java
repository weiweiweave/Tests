package com.finance.banking.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerIdDTO {
    private Long id;
    public CustomerIdDTO(Long id) {
        this.id = id;
    }
}
