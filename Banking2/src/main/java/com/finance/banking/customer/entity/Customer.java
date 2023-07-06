package com.finance.banking.customer.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company")
    @CsvBindByName(column = "Company")
    private String company;

    @Column(name = "first_Name")
    @CsvBindByName(column = "FirstName")
    private String firstName;

    @Column(name = "last_Name")
    @CsvBindByName(column = "LastName")
    private String lastName;

    @Column(name = "address")
    @CsvBindByName(column = "Address")
    private String address;

    @Column(name = "email_Address")
    @CsvBindByName(column = "Email")
    private String emailAddress;

    @Column(name = "nric")
    private String nric;

    @Column(name = "fund_Source")
    private String fundSource;

    @Column(name = "job_Title")
    private String jobTitle;

    @Column(name = "job_Description")
    private String jobDescription;

    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "creation_Date_Time")
    private LocalDateTime creationDateTime;

    public Customer(String company, String firstName, String lastName, String address, String emailAddress) {
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
    }

    public Customer(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", nric='" + nric + '\'' +
                ", fundSource='" + fundSource + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
