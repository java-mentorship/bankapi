package br.com.leonardoraupp.bankmanager.application.exception;

public class InvalidUserCredentialsException extends Exception {
    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
