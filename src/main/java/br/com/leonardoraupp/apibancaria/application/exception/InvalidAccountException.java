package br.com.leonardoraupp.apibancaria.application.exception;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}
