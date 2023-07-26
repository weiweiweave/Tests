package com.digital.ace.java.banking.account.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBankAccountDTO {

    @CsvBindByName(column = "Uuid")
    private String uuid;

    @CsvBindByName(column = "StaffIdWhoKeyIn")
    private String staffIdWhoKeyIn;

    @CsvBindByName(column = "CreatedDate")
    private String createdDate;

    @CsvBindByName(column = "CustomerNric")
    private String customerNric;

    @CsvBindByName(column = "InterestRate")
    private String interestRate;

    @CsvBindByName(column = "Balance")
    private String balance;

    @CsvBindByName(column = "MinAmountToCalInterest")
    private String minAmountToCalInterest;

    @CsvBindByName(column = "AccountNo")
    private String accountNo;
}
