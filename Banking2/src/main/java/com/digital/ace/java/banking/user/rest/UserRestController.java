package com.digital.ace.java.banking.user.rest;


import com.digital.ace.java.banking.exception.ExceptionJSONInfo;
import com.digital.ace.java.banking.exception.UserNotFoundException;
import com.digital.ace.java.banking.user.dao.UserRepository;
import com.digital.ace.java.banking.user.dto.CreateUserRequest;
import com.digital.ace.java.banking.user.dto.UserDTO;
import com.digital.ace.java.banking.user.dto.UserIdDTO;
import com.digital.ace.java.banking.user.entity.User;
import com.digital.ace.java.banking.user.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import com.digital.ace.java.banking.user.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @PostMapping("/user")
    public UserIdDTO createUser(@RequestBody @Valid CreateUserRequest createUserRequest)  throws Exception {
        User user = UserMapper.toUser(createUserRequest);

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

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable("id") Long id) throws UserNotFoundException {

        Optional<User> optionalUser = userService.find(id);
        User user = new User();

        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        else {
            throw new UserNotFoundException(id);
        }
        return UserMapper.toDTO(user);
    }

    //Controller Based Exception Handling
    //to handle exceptions thrown by request handling (@RequestMapping)
    //methods in the same controller.
    //Handle exceptions without the @ResponseStatus annotation
    // (typically predefined exceptions that you didn't write)
    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody ExceptionJSONInfo handleNoSuchElementException(HttpServletRequest request, Exception ex) {

        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());

        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ExceptionJSONInfo handleValidationExceptions(
            HttpServletRequest request,
            MethodArgumentNotValidException ex) {

        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setMessage(errors.toString());

        return response;
    }
}
