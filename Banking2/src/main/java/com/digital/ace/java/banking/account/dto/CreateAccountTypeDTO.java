package com.digital.ace.java.banking.account.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateAccountTypeDTO {

    @CsvBindByName(column = "AccountDescription")
    private String accountDescription;
}
