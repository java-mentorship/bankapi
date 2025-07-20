package br.com.leonardoraupp.apibancaria.controller;

import br.com.leonardoraupp.apibancaria.application.OpenAccountUseCase;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final OpenAccountUseCase openAccountUseCase;

    public AccountController(OpenAccountUseCase openAccountUseCase) {
        this.openAccountUseCase = openAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<?> openAccount(@RequestBody OpenAccountRequest request) throws InvalidAccountException {
        OpenAccountResponse response = openAccountUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
