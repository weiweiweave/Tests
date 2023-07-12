package com.finance.banking.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(HttpServletRequest request, Exception exception) {
        //ResponseEntity<Object>
        //return new ResponseEntity<>("DataIntegrityViolationException", HttpStatus.INTERNAL_SERVER_ERROR);
        logger.trace("SQLException Occured:: URL="+request.getRequestURL());
        return "database_error";
    }
}
