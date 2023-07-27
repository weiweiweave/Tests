package com.digital.ace.java.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception when processing a web-request
//to return specific HTTP status-code
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Item Not Found") //404
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String item){

        super("ItemNotFoundException with column="+item,null,false,false);
    }
}
