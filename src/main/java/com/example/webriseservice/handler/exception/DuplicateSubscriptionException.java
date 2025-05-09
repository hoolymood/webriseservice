package com.example.webriseservice.handler.exception;

public class DuplicateSubscriptionException extends RuntimeException{

    public DuplicateSubscriptionException(String message){
        super(message);
    }
}
