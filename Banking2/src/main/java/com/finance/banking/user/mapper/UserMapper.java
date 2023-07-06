package com.finance.banking.user.mapper;

import com.finance.banking.user.dto.CreateUserDTO;
import com.finance.banking.user.dto.UserDTO;
import com.finance.banking.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String address = user.getAddress();

        return new UserDTO(firstName,lastName,address);
    }

    public User toUser(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.getFirstName(), createUserDTO.getLastName(), createUserDTO.getAddress());
    }
}
