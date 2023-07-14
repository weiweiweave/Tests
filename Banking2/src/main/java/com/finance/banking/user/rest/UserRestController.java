package com.finance.banking.user.rest;


import com.finance.banking.exception.ExceptionJSONInfo;
import com.finance.banking.exception.UserNotFoundException;
import com.finance.banking.user.dao.UserRepository;
import com.finance.banking.user.dto.CreateUserDTO;
import com.finance.banking.user.dto.UserDTO;
import com.finance.banking.user.dto.UserIdDTO;
import com.finance.banking.user.mapper.UserMapper;
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

import com.finance.banking.user.entity.User;
import com.finance.banking.user.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRestController {

    private static final String template = "Hello, %s!";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    //private UserMapper mapper = new UserMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/user")
    public UserIdDTO createUser(@RequestBody @Valid CreateUserDTO createUserDTO)  throws Exception {
        User user = UserMapper.toUser(createUserDTO);

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
        logger.trace(userDTOList.toString());
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

    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody ExceptionJSONInfo handleNoSuchElementException(HttpServletRequest request, Exception ex) {

        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());

        return response;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody ExceptionJSONInfo handleUserNotFoundException(HttpServletRequest request, Exception ex) {

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
