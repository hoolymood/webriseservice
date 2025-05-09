package com.example.webriseservice.handler;

import com.example.webriseservice.handler.exception.DuplicateSubscriptionException;
import com.example.webriseservice.handler.exception.SubscriptionNotFoundException;
import com.example.webriseservice.handler.exception.UserAlreadyExistsException;
import com.example.webriseservice.handler.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> duplicateSubscriptionException(DuplicateSubscriptionException e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> subscriptionNotFoundException(SubscriptionNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }
}
