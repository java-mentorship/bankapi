package br.com.leonardoraupp.bankmanager.application.service;

import br.com.leonardoraupp.bankmanager.application.exception.InvalidUserException;
import br.com.leonardoraupp.bankmanager.domain.User;

public interface AuthService {
    String authenticate(User user) throws InvalidUserException;
}
