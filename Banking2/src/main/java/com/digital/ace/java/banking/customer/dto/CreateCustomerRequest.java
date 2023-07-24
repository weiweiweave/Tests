package com.digital.ace.java.banking.customer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequest {

    private String uuid;

    @NotNull(message = "Invalid staffIdWhoKeyIn: staffIdWhoKeyIn is NULL")
    private String staffIdWhoKeyIn;

    private String company;

    private String fundSource;

    private String address;

    private String city;

    private String joinedDate;

    private String nric;

    private String firstName;

    private String lastName;

    private String sex;

    private String emailAddress;

    private String phone;

    private String dateOfBirth;

    private String jobTitle;
}
