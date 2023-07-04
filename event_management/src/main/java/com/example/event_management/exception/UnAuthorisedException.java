package com.example.event_management.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(code = UNAUTHORIZED)
public class UnAuthorisedException extends RuntimeException {
    public UnAuthorisedException(String message){
        super(message);
    }
}

