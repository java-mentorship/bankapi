package br.com.leonardoraupp.bankmanager.application;

import br.com.leonardoraupp.bankmanager.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.application.request.GetHolderBalanceRequest;
import br.com.leonardoraupp.bankmanager.application.response.GetHolderBalanceResponse;
import br.com.leonardoraupp.bankmanager.application.service.AccountService;
import br.com.leonardoraupp.bankmanager.domain.Account;

import java.time.LocalDateTime;

public class GetBalanceUseCase {

    private final AccountService accountService;

    public GetBalanceUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public GetHolderBalanceResponse execute(Integer id, GetHolderBalanceRequest request) throws InvalidHolderException, AccountNotFoundException {
        Account account = accountService.getAccount(id, request.cpf());
        return new GetHolderBalanceResponse(LocalDateTime.now(), account.getBalance());
    }
}
