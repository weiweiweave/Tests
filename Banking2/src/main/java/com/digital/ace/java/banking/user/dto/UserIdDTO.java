package com.digital.ace.java.banking.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdDTO {
    private Long id;
    public UserIdDTO(Long id) {
        this.id = id;
    }
}
