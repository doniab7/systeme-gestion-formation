package com.prestacode.systgestionformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FormationNotFoundException extends RuntimeException{

    public FormationNotFoundException(String message) {
        super(message);
    }
}
