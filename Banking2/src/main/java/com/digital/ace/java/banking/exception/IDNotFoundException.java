package com.digital.ace.java.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception when processing a web-request
//to return specific HTTP status-code
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="ID Not Found") //404
public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(Long id){

        super("IDNotFoundException with id="+id,null,false,false);
    }
}
