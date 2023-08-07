package com.digital.ace.java.banking.role.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bank_Roles")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_user_id")
    private Long bankUserId;

    @Column(name = "role")
    private String role;

    public Role(Long bankUserId, String role) {
        this.bankUserId = bankUserId;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", bankUserId=" + bankUserId +
                ", role='" + role + '\'' +
                '}';
    }
}
