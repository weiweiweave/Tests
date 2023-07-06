package com.finance.banking.user.entity;


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
@Table(name = "bank_Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @CsvBindByName(column = "Username")
    private String username;

    @Column(name = "password")
    @CsvBindByName(column = "Password")
    private String password;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "address")
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

    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
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
