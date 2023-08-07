package com.digital.ace.java.banking.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankTransactionRequest {

    @NotBlank(message = "Invalid UUID: Empty UUID")
    @NotNull(message = "Invalid UUID: UUID is NULL")
    private String uuid;

    @NotBlank(message = "Invalid staffIdWhoKeyIn: Empty staffIdWhoKeyIn")
    @NotNull(message = "Invalid staffIdWhoKeyIn: staffIdWhoKeyIn is NULL")
    private String staffIdWhoKeyIn;

    @NotBlank(message = "Invalid accountNo: Empty accountNo")
    @NotNull(message = "Invalid accountNo: accountNo is NULL")
    private String accountNo;

    @NotBlank(message = "Invalid amount: Empty amount")
    @NotNull(message = "Invalid amount: amount is NULL")
    private String amount;

    @NotBlank(message = "Invalid isCredit: Empty isCredit")
    @NotNull(message = "Invalid isCredit: isCredit is NULL")
    private String isCredit;

    private String remarks;

}
