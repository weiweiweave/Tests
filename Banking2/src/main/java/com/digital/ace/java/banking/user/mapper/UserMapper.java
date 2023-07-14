package com.digital.ace.java.banking.user.mapper;

import com.digital.ace.java.banking.user.dto.CreateUserDTO;
import com.digital.ace.java.banking.user.dto.UserDTO;
import com.digital.ace.java.banking.user.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        String username = user.getUsername();
        String email = user.getEmailAddress();

        return new UserDTO(username,email);
    }

    public static User toUser(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getUsername(), createUserDTO.getPassword(), createUserDTO.getEmail(), LocalDateTime.now());
    }
}
