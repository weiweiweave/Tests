package com.digital.ace.java.banking.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdDTO {
    private String username;
    public UserIdDTO(String username) {
        this.username = username;
    }
}
