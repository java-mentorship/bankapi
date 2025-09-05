package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidUserException;
import br.com.leonardoraupp.apibancaria.application.request.CreateUserRequest;
import br.com.leonardoraupp.apibancaria.application.response.CreateUserResponse;
import br.com.leonardoraupp.apibancaria.application.service.UserService;
import br.com.leonardoraupp.apibancaria.domain.User;
import br.com.leonardoraupp.apibancaria.utility.UserMapper;

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
