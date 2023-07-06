package com.finance.banking.user.mapper;

import com.finance.banking.user.dto.CreateUserDTO;
import com.finance.banking.user.dto.UserDTO;
import com.finance.banking.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        String username = user.getUsername();
        String email = user.getEmailAddress();

        return new UserDTO(username,email);
    }

    public User toUser(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getUsername(), createUserDTO.getEmail());
    }
}
