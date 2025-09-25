package com.example.demo.Exceptions;

public final class CustomExceptions
{
    public static final class LogInException extends RuntimeException {
        public LogInException() {
            super("Log In Error");
        }
    }

    public static final class RegisterException extends RuntimeException {
        public RegisterException() {
            super("Register Error");
        }
    }

    public static final class UserNotFound extends RuntimeException {
        public UserNotFound() {
            super("User Not Found");
        }
    }
}
