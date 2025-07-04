package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;

public class OpenAccountUseCase {

    private final AccountService accountService;

    public OpenAccountUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public OpenAccountResponse execute(OpenAccountRequest request) {
//        accountService.createAccount();  // OpenAccountRequest -> Account(domain) -> OpenAccountResponse
        return null;
    }
}
