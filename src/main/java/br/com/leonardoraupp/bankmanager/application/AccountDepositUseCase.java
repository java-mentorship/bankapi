package br.com.leonardoraupp.bankmanager.application;

import br.com.leonardoraupp.bankmanager.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidAccountException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.application.request.AccountTransactionRequest;
import br.com.leonardoraupp.bankmanager.application.response.TransactionResponse;
import br.com.leonardoraupp.bankmanager.application.service.AccountService;
import br.com.leonardoraupp.bankmanager.domain.Transaction;
import br.com.leonardoraupp.bankmanager.utility.TransactionMapper;

public class AccountDepositUseCase {
    private final AccountService accountService;

    public AccountDepositUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public TransactionResponse execute(Integer accountId, AccountTransactionRequest request) throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        Transaction deposit = accountService.deposit(accountId, request.amount(), request.cpf());
        return TransactionMapper.toTransactionResponse(deposit);
    }
}
