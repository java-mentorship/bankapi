package br.com.leonardoraupp.apibancaria.application.exception;

public class InvalidUserCredentialsException extends Exception {
    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
