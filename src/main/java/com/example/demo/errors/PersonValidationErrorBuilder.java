package com.example.demo.errors;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class PersonValidationErrorBuilder {

    public static PersonValidationError fromBindingErrors(Errors errors) {
        PersonValidationError error = new PersonValidationError();
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addError(objectError.getDefaultMessage());
        }
        return error;
    }
}
