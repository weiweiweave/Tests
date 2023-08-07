package com.digital.ace.java.banking.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankAccountRequest {

    @NotBlank(message = "Invalid UUID: Empty UUID")
    @NotNull(message = "Invalid UUID: UUID is NULL")
    private String uuid;

    @NotBlank(message = "Invalid staffIdWhoKeyIn: Empty staffIdWhoKeyIn")
    @NotNull(message = "Invalid staffIdWhoKeyIn: staffIdWhoKeyIn is NULL")
    private String staffIdWhoKeyIn;

    private String createdDate;

    @NotBlank(message = "Invalid customerNric: Empty customerNric")
    @NotNull(message = "Invalid customerNric: customerNric is NULL")
    private String customerNric;

    @NotBlank(message = "Invalid balance: Empty balance")
    @NotNull(message = "Invalid balance: balance is NULL")
    private String balance;

    @NotBlank(message = "Invalid accountNo: Empty accountNo")
    @NotNull(message = "Invalid accountNo: accountNo is NULL")
    private String accountNo;

    @NotBlank(message = "Invalid accountType: Empty accountType")
    @NotNull(message = "Invalid accountType: accountType is NULL")
    private String accountType;

    @NotBlank(message = "Invalid interestRate: Empty interestRate")
    @NotNull(message = "Invalid interestRate: interestRate is NULL")
    private String interestRate;

    @NotBlank(message = "Invalid minAmountToCalInterest: Empty minAmountToCalInterest")
    @NotNull(message = "Invalid minAmountToCalInterest: minAmountToCalInterest is NULL")
    private String minAmountToCalInterest;
}
