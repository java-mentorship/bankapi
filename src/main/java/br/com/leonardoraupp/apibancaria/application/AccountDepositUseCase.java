package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.request.AccountDepositRequest;
import br.com.leonardoraupp.apibancaria.application.response.TransactionResponse;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.domain.Transaction;
import br.com.leonardoraupp.apibancaria.utility.TransactionMapper;

public class AccountDepositUseCase {
    private final AccountService accountService;

    public AccountDepositUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public TransactionResponse execute(Integer accountId, AccountDepositRequest request) throws AccountNotFoundException, InvalidHolderException {
        Transaction deposit = accountService.deposit(accountId, request.amount());
        return TransactionMapper.toTransactionResponse(deposit);
    }
}
