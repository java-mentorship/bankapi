package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.request.AccountTransferenceTransactionRequest;
import br.com.leonardoraupp.apibancaria.application.response.TransactionResponse;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.domain.Transaction;
import br.com.leonardoraupp.apibancaria.utility.TransactionMapper;

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
