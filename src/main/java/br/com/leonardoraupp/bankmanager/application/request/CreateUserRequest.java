package br.com.leonardoraupp.bankmanager.application.request;

public record CreateUserRequest(String username, String password, String profile) {
}
