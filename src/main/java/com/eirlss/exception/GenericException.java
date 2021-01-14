package com.eirlss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GenericException extends RuntimeException {
    public GenericException(String exception) {
        super(exception);
    }
}
