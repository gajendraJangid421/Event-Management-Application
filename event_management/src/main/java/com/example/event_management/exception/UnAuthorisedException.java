package com.example.event_management.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(code = UNAUTHORIZED)
@Getter
public class UnAuthorisedException extends RuntimeException {
    private HttpStatus httpStatus= UNAUTHORIZED;
    public UnAuthorisedException(String message){
        super(message);
    }

}

