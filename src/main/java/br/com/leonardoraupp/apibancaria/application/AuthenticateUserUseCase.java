package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidUserException;
import br.com.leonardoraupp.apibancaria.application.request.AuthenticationUserRequest;
import br.com.leonardoraupp.apibancaria.application.service.AuthService;
import br.com.leonardoraupp.apibancaria.utility.UserMapper;

public class AuthenticateUserUseCase {
    private final AuthService authService;

    public AuthenticateUserUseCase(AuthService authService) {
        this.authService = authService;
    }

    public String execute(AuthenticationUserRequest request) throws InvalidUserException {
        return authService.authenticate(UserMapper.toDomain(request));
    }
}
