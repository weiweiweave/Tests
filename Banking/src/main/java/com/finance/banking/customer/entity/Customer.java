package com.finance.banking.customer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


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

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email_Address")
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

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
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
