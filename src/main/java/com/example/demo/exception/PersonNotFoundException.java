package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(long id) {
        super("Could not find person: " + id);
    }
}
