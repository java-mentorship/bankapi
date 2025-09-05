package br.com.leonardoraupp.apibancaria.application.service;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidUserException;
import br.com.leonardoraupp.apibancaria.domain.User;

public interface AuthService {
    String authenticate(User user) throws InvalidUserException;
}
