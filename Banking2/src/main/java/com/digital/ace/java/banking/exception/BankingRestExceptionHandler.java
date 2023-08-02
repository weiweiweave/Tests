package com.digital.ace.java.banking.exception;

import com.digital.ace.java.banking.common.BankingErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BankingRestExceptionHandler {
    // 404
    @ExceptionHandler
    public ResponseEntity<BankingErrorResponse> handleException(UserNotFoundException exc) {
        // create a BankingErrorResponse
        BankingErrorResponse error = new BankingErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(LocalDateTime.now());

        // return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
