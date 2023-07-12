package com.finance.banking.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public @ResponseBody ExceptionJSONInfo handleDataIntegrityViolation(HttpServletRequest request, Exception ex) {
        //ResponseEntity<Object>
        //return new ResponseEntity<>("DataIntegrityViolationException", HttpStatus.INTERNAL_SERVER_ERROR);
        logger.trace("DataIntegrityViolationException Occured:: Cause="+ ExceptionUtils.getRootCauseMessage(ex));
        //return "database_error";

        logger.trace("DataIntegrityViolationException Occured:: Cause="+ ExceptionUtils.getStackTrace(ex));




        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        //response.setMessage(ex.getStackTrace().toString());
        response.setCause(ex.getLocalizedMessage());

        return response;
    }
}
