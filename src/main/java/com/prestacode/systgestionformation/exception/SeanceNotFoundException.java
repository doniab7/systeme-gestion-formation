package com.prestacode.systgestionformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeanceNotFoundException extends RuntimeException{

    public SeanceNotFoundException(String message) {
        super(message);
    }

}
