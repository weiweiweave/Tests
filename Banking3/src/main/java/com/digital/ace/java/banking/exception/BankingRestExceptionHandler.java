package com.digital.ace.java.banking.exception;

import com.digital.ace.java.banking.common.BankingErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

// global exception handler

@ControllerAdvice
public class BankingRestExceptionHandler {
    // 404 Not Found = The server cannot find the requested resource.
    @ExceptionHandler
    public ResponseEntity<BankingErrorResponse> handleException(ItemNotFoundException exc) {
        // create a BankingErrorResponse
        BankingErrorResponse error = new BankingErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(LocalDateTime.now());

        // return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    // 400 Bad Request = The server cannot or will not process the request due to something that is perceived to be a client error.
    @ExceptionHandler
    public ResponseEntity<BankingErrorResponse> handleException(InsufficientBalanceException exc) {
        // create a BankingErrorResponse
        BankingErrorResponse error = new BankingErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(LocalDateTime.now());

        // return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
