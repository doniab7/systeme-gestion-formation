package com.prestacode.systgestionformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class TrancheNotFoundException extends RuntimeException{
    public TrancheNotFoundException(String message) {
        super(message);
    }
}
