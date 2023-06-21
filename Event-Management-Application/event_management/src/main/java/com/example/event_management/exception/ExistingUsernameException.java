package com.example.event_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ExistingUsernameException extends RuntimeException{

    public ExistingUsernameException(String message){
        super(message);
    }
}
