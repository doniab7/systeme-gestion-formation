package com.prestacode.systgestionformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class PresenceNotFoundException extends RuntimeException{

    public PresenceNotFoundException(String message) {
        super(message);
    }
}
