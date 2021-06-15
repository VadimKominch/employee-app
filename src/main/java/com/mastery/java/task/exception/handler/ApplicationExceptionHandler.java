package com.mastery.java.task.exception.handler;

import com.mastery.java.task.exception.EmployeeServiceException;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFoundUSer(EmployeeNotFoundException e) {
        logger.error("User with {} not found",e.getId());
        return new ResponseEntity<String>(String.format("User with id %d doesn't exist",e.getId()), HttpStatus.NOT_FOUND);
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception e) {
        EmployeeServiceException ex = new EmployeeServiceException(e);
        logger.error("Exception  {}",ex.toString());
        return new ResponseEntity<String>("Exception", HttpStatus.BAD_REQUEST);
    }*/




}
