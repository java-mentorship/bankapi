package br.com.leonardoraupp.apibancaria.application.exception;

public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
