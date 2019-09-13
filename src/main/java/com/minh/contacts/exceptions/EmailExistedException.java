package com.minh.contacts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailExistedException extends RuntimeException {

    public EmailExistedException(String message) {
        super(message);
    }
}