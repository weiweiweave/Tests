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

    @Column(name = "staff_id_who_key_in")
    @CsvBindByName(column = "StaffIdWhoKeyIn")
    private String staffIdWhoKeyIn;

    @Column(name = "company")
    @CsvBindByName(column = "Company")
    private String company;

    @Column(name = "fund_Source")
    @CsvBindByName(column = "SourceOfFund")
    private String fundSource;

    @Column(name = "address")
    @CsvBindByName(column = "Address")
    private String address;

    @Column(name = "city")
    @CsvBindByName(column = "City")
    private String city;

    @Column(name = "joined_Date")
    @CsvBindByName(column = "JoinedDate")
    private String joinedDate;



    @Column(name = "first_Name")
    @CsvBindByName(column = "FirstName")
    private String firstName;

    @Column(name = "last_Name")
    @CsvBindByName(column = "LastName")
    private String lastName;

    @Column(name = "email_Address")
    @CsvBindByName(column = "Email")
    private String emailAddress;

    @Column(name = "nric")
    private String nric;

    @Column(name = "job_Title")
    private String jobTitle;

    @Column(name = "job_Description")
    private String jobDescription;

    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "creation_Date_Time")
    private LocalDateTime creationDateTime;

    public Customer(String staffIdWhoKeyIn, String company, String fundSource, String address, String city, String joinedDate, String firstName, String lastName, String emailAddress) {
        this.staffIdWhoKeyIn = staffIdWhoKeyIn;
        this.company = company;
        this.fundSource = fundSource;
        this.address = address;
        this.city = city;
        this.joinedDate = joinedDate;

        this.firstName = firstName;
        this.lastName = lastName;

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
