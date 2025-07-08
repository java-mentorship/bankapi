package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.domain.Account;

public class OpenAccountUseCase {

    private final AccountService accountService;

    public OpenAccountUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public OpenAccountResponse execute(OpenAccountRequest request) {
        Account account = accountService.createAccount(AccountMapper.toEntity(request));
        return AccountMapper.toDTO(account);
    }
}
