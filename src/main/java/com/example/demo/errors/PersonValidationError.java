package com.example.demo.errors;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonValidationError {

    private List<String> errors = new ArrayList<>();

    public void addError(String errorMessage) {
        errors.add(errorMessage);
    }
}
