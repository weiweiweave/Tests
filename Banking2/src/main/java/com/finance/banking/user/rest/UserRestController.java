package com.finance.banking.user.rest;


import com.finance.banking.user.dto.CreateUserDTO;
import com.finance.banking.user.dto.UserDTO;
import com.finance.banking.user.dto.UserIdDTO;
import com.finance.banking.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import com.finance.banking.user.entity.User;
import com.finance.banking.user.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRestController {

    private static final String template = "Hello, %s!";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    private UserMapper mapper = new UserMapper();

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/user")
    public UserIdDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = mapper.toUser(createUserDTO);

        user.setId(Long.valueOf(0));

        //logger.trace(user.toString());

        User savedUser = userService.save(user);

        //logger.trace(savedUser.getId().toString());

        return new UserIdDTO(savedUser.getId());
    }


    @GetMapping("/users")
    public List<UserDTO> listUsers() {
        List<User> userList = userService.findAll();
        //logger.trace(userList.toString());
        List<UserDTO> userDTOList = userList.stream().map(UserMapper::toDTO).collect(Collectors.toList());
        //logger.trace(userDTOList.toString());
        return userDTOList;
    }
}
