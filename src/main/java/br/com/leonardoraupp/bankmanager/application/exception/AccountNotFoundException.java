package br.com.leonardoraupp.bankmanager.application.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
