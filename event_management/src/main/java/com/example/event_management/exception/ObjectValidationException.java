package com.example.event_management.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.LENGTH_REQUIRED;

@ResponseStatus(code = LENGTH_REQUIRED)
public class ObjectValidationException extends RuntimeException{
    public ObjectValidationException(String message){
        super(message);
    }
}
