package com.digital.ace.java.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception when processing a web-request
//to return specific HTTP status-code
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User Not Found") //404
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id){

        super("UserNotFoundException with id="+id,null,false,false);
    }
}
