package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private String message;
    private LocalDateTime time;

    public ApiError(HttpStatus status, Throwable ex) {
        time = LocalDateTime.now();
        this.status = status;
        this.message = ex.getMessage();
    }
}
