package com.digital.ace.java.banking.customer.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "staff_id_who_key_in")
    private String staffIdWhoKeyIn;

    @Column(name = "company")
    private String company;

    @Column(name = "fund_Source")
    private String fundSource;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "joined_Date")
    private LocalDate joinedDate;

    @Column(name = "nric")
    private String nric;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email_Address")
    private String emailAddress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "job_Title")
    private String jobTitle;

    @Column(name = "creation_Date_Time")
    private LocalDateTime creationDateTime;

    public Customer(String uuid, String staffIdWhoKeyIn, String company, String fundSource, String address, String city, LocalDate joinedDate,
            String nric, String firstName, String lastName, String sex, String emailAddress, String phone, LocalDate dateOfBirth, String jobTitle,
                    LocalDateTime creationDateTime) {
        this.uuid = uuid;
        this.staffIdWhoKeyIn = staffIdWhoKeyIn;
        this.company = company;
        this.fundSource = fundSource;
        this.address = address;
        this.city = city;
        this.joinedDate = joinedDate;
        this.nric = nric;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
        this.creationDateTime = creationDateTime;
    }
}
