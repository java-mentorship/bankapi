package br.com.leonardoraupp.bankmanager.application;

import br.com.leonardoraupp.bankmanager.application.exception.InvalidUserException;
import br.com.leonardoraupp.bankmanager.application.request.CreateUserRequest;
import br.com.leonardoraupp.bankmanager.application.response.CreateUserResponse;
import br.com.leonardoraupp.bankmanager.application.service.UserService;
import br.com.leonardoraupp.bankmanager.domain.User;
import br.com.leonardoraupp.bankmanager.utility.UserMapper;

public class CreateUserUseCase {
    private final UserService userService;

    public CreateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponse execute(CreateUserRequest request) throws InvalidUserException {
        User user = userService.register(UserMapper.toDomain(request));
        return new CreateUserResponse("User created successfully.", user.getId(), user.getRegistrationDate());
    }
}
