package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.utility.AccountMapper;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.domain.Account;

public class OpenAccountUseCase {

    private final AccountService accountService;

    public OpenAccountUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public OpenAccountResponse execute(OpenAccountRequest request) throws InvalidAccountException, InvalidHolderException {
        Account account = accountService.createAccount(AccountMapper.toDomain(request));
        return AccountMapper.toAccountDTO(account);
    }
}
