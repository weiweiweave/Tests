package com.digital.ace.java.banking.transaction.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateSavingDepositTransactionDTO {

    @CsvBindByName(column = "Uuid")
    private String uuid;

    @CsvBindByName(column = "StaffIdWhoKeyIn")
    private String staffIdWhoKeyIn;

    @CsvBindByName(column = "AccountNo")
    private String accountNo;

    @CsvBindByName(column = "Amount")
    private String amount;

    @CsvBindByName(column = "IsCredit")
    private String isCredit;

    @CsvBindByName(column = "Remarks")
    private String remarks;

}
