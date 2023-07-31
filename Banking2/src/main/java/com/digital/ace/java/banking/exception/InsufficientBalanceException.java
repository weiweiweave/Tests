package com.digital.ace.java.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception when processing a web-request
//to return specific HTTP status-code
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Insufficient Balance") //404
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String account){

        super("InsufficientBalanceException with account="+account,null,false,false);
    }
}
