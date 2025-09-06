package br.com.leonardoraupp.bankmanager.application;

import br.com.leonardoraupp.bankmanager.utility.AccountMapper;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidAccountException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.application.request.OpenAccountRequest;
import br.com.leonardoraupp.bankmanager.application.response.OpenAccountResponse;
import br.com.leonardoraupp.bankmanager.application.service.AccountService;
import br.com.leonardoraupp.bankmanager.domain.Account;

public class OpenAccountUseCase {

    private final AccountService accountService;

    public OpenAccountUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public OpenAccountResponse execute(OpenAccountRequest request) throws InvalidAccountException, InvalidHolderException {
        Account account = accountService.createAccount(AccountMapper.toDomain(request));
        return AccountMapper.toOpenAccountResponse(account);
    }
}
