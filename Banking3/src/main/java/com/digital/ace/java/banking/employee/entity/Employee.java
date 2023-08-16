package com.digital.ace.java.banking.employee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bank_Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "active")
    private Integer active;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    public Employee(String username, String password, String emailAddress, Integer active, LocalDateTime creationDateTime) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.active = active;
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
