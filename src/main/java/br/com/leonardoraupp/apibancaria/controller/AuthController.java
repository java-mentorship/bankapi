package br.com.leonardoraupp.apibancaria.controller;

import br.com.leonardoraupp.apibancaria.application.AuthenticateUserUseCase;
import br.com.leonardoraupp.apibancaria.application.CreateUserUseCase;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidUserException;
import br.com.leonardoraupp.apibancaria.application.request.AuthenticationUserRequest;
import br.com.leonardoraupp.apibancaria.application.request.CreateUserRequest;
import br.com.leonardoraupp.apibancaria.application.response.CreateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    public AuthController(CreateUserUseCase createUserUseCase, AuthenticateUserUseCase authenticateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> register(@RequestBody CreateUserRequest request) throws InvalidUserException {
        CreateUserResponse response = createUserUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationUserRequest request) throws InvalidUserException {
        String response = authenticateUserUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
