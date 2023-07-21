package com.digital.ace.java.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Customer Not Found") //404
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(Long id){

        super("CustomerNotFoundException with id="+id,null,false,false);
    }
}
