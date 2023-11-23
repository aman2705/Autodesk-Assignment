package com.assignment.autodesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EncodingException extends RuntimeException{
    public EncodingException(String message){
        super("Encoding Exception");
    }
}
