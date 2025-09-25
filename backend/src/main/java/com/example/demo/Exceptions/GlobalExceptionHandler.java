package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.example.demo.Exceptions.CustomExceptions;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(CustomExceptions.LogInException.class)
    public ResponseEntity<String> invalidLogIn()
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed Log In");
    }

    @ExceptionHandler(CustomExceptions.RegisterException.class)
    public ResponseEntity<String> failedToRegister()
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed Register");
    }

    @ExceptionHandler(CustomExceptions.UserNotFound.class)
    public ResponseEntity<String> userNotFound()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
