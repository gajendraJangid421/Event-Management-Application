package com.example.event_management.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(code = CONFLICT)
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String message){
        super(message);
    }
}
