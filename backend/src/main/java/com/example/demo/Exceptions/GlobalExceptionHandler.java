package com.example.demo.Exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.example.demo.Exceptions.CustomExceptions;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;

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
}
