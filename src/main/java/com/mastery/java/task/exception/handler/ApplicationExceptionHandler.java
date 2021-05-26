package com.mastery.java.task.exception.handler;

import com.mastery.java.task.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    public ResponseEntity handleWrongEntityException() {
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFoundUSer(UserNotFoundException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
