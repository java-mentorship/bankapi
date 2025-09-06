package br.com.leonardoraupp.bankmanager.application;

import br.com.leonardoraupp.bankmanager.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidAccountException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.application.request.AccountTransferenceTransactionRequest;
import br.com.leonardoraupp.bankmanager.application.response.TransactionResponse;
import br.com.leonardoraupp.bankmanager.application.service.AccountService;
import br.com.leonardoraupp.bankmanager.domain.Transaction;
import br.com.leonardoraupp.bankmanager.utility.TransactionMapper;

public class AccountTransferenceUseCase {
    private final AccountService accountService;

    public AccountTransferenceUseCase(AccountService accountService) {
        this.accountService = accountService;
    }

    public TransactionResponse execute(Integer accountId, AccountTransferenceTransactionRequest request) throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        Transaction transference = accountService.transference(accountId, request.amount(), request.cpfReceiver(), request.receiverAccountId());
        return TransactionMapper.toTransactionResponse(transference);
    }
}
