package com.prestacode.systgestionformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentAmountExceededException extends RuntimeException{

    public PaymentAmountExceededException(String message) {
        super(message);
    }

}

