package com.digital.ace.java.banking.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequest {

    @NotBlank(message = "Invalid UUID: Empty UUID")
    @NotNull(message = "Invalid UUID: UUID is NULL")
    private String uuid;

    @NotBlank(message = "Invalid staffIdWhoKeyIn: Empty staffIdWhoKeyIn")
    @NotNull(message = "Invalid staffIdWhoKeyIn: staffIdWhoKeyIn is NULL")
    private String staffIdWhoKeyIn;

    private String company;

    private String fundSource;

    private String address;

    private String city;

    private String joinedDate;

    @NotBlank(message = "Invalid NRIC: Empty NRIC")
    @NotNull(message = "Invalid NRIC: NRIC is NULL")
    private String nric;

    @NotBlank(message = "Invalid firstName: Empty firstName")
    @NotNull(message = "Invalid firstName: firstName is NULL")
    private String firstName;

    @NotBlank(message = "Invalid lastName: Empty lastName")
    @NotNull(message = "Invalid lastName: lastName is NULL")
    private String lastName;

    private String sex;

    private String emailAddress;

    private String phone;

    private String dateOfBirth;

    private String jobTitle;
}
