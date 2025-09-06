package br.com.leonardoraupp.bankmanager.application;

import br.com.leonardoraupp.bankmanager.application.exception.InvalidUserException;
import br.com.leonardoraupp.bankmanager.application.request.AuthenticationUserRequest;
import br.com.leonardoraupp.bankmanager.application.service.AuthService;
import br.com.leonardoraupp.bankmanager.utility.UserMapper;

public class AuthenticateUserUseCase {
    private final AuthService authService;

    public AuthenticateUserUseCase(AuthService authService) {
        this.authService = authService;
    }

    public String execute(AuthenticationUserRequest request) throws InvalidUserException {
        return authService.authenticate(UserMapper.toDomain(request));
    }
}
