package com.finance.banking.user.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Column(name = "email_Address")
    @CsvBindByName(column = "Email")
    private String emailAddress;

    @Column(name = "creation_Date_Time")
    private LocalDateTime creationDateTime;

    public User(String username, String password, String emailAddress, LocalDateTime creationDateTime) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.creationDateTime = creationDateTime;
    }

    public User(String username, String emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
