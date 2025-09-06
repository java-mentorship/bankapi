package br.com.leonardoraupp.bankmanager.application.exception;

public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
