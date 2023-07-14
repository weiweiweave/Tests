package com.digital.ace.java.banking.customer.dto;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerDTO {

    @CsvBindByName(column = "StaffIdWhoKeyIn")
    private String staffIdWhoKeyIn;

    @CsvBindByName(column = "Company")
    private String company;

    @CsvBindByName(column = "SourceOfFund")
    private String fundSource;

    @CsvBindByName(column = "Address")
    private String address;

    @CsvBindByName(column = "City")
    private String city;

    @CsvBindByName(column = "JoinedDate")
    private String joinedDate;

    @CsvBindByName(column = "Nric")
    private String nric;

    @CsvBindByName(column = "FirstName")
    private String firstName;

    @CsvBindByName(column = "LastName")
    private String lastName;

    @CsvBindByName(column = "Sex")
    private String sex;

    @CsvBindByName(column = "Email")
    private String emailAddress;

    @CsvBindByName(column = "Phone")
    private String phone;

    @CsvBindByName(column = "DateOfBirth")
    private String dateOfBirth;

    @CsvBindByName(column = "JobTitle")
    private String jobTitle;
}
