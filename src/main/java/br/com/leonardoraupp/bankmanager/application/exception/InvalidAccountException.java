package br.com.leonardoraupp.bankmanager.application.exception;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}
