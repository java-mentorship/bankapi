package br.com.leonardoraupp.apibancaria.controller;

import br.com.leonardoraupp.apibancaria.application.*;
import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.request.AccountTransactionRequest;
import br.com.leonardoraupp.apibancaria.application.request.AccountTransferenceTransactionRequest;
import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.GetHolderBalanceResponse;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;

import br.com.leonardoraupp.apibancaria.application.request.GetHolderBalanceRequest;
import br.com.leonardoraupp.apibancaria.application.response.TransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final OpenAccountUseCase openAccountUseCase;
    private final GetBalanceUseCase getBalanceUseCase;
    private final AccountDepositUseCase accountDepositUseCase;
    private final AccountWithdrawUseCase accountWithdrawUseCase;
    private final AccountTransferenceUseCase accountTransferenceUseCase;

    public AccountController(OpenAccountUseCase openAccountUseCase, GetBalanceUseCase getBalanceUseCase, AccountDepositUseCase accountDepositUseCase, AccountWithdrawUseCase accountWithdrawUseCase, AccountTransferenceUseCase accountTransferenceUseCase) {
        this.openAccountUseCase = openAccountUseCase;
        this.getBalanceUseCase = getBalanceUseCase;
        this.accountDepositUseCase = accountDepositUseCase;
        this.accountWithdrawUseCase = accountWithdrawUseCase;
        this.accountTransferenceUseCase = accountTransferenceUseCase;
    }

    @PostMapping
    public ResponseEntity<OpenAccountResponse> openAccount(@RequestBody OpenAccountRequest request) throws InvalidHolderException, InvalidAccountException {
        OpenAccountResponse response = openAccountUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetHolderBalanceResponse> getBalance(@PathVariable Integer id, @RequestBody GetHolderBalanceRequest getBalanceRequest)
            throws AccountNotFoundException, InvalidHolderException {
        GetHolderBalanceResponse response = getBalanceUseCase.execute(id, getBalanceRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<TransactionResponse> deposit(@PathVariable Integer id, @RequestBody AccountTransactionRequest request)
            throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        TransactionResponse response = accountDepositUseCase.execute(id, request);
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@PathVariable Integer id, @RequestBody AccountTransactionRequest request)
            throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        TransactionResponse response = accountWithdrawUseCase.execute(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/transference")
    public ResponseEntity<TransactionResponse> transference(@PathVariable Integer id, @RequestBody AccountTransferenceTransactionRequest request)
            throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        TransactionResponse response = accountTransferenceUseCase.execute(id, request);
        return ResponseEntity.ok(response);
    }
    // TODO: Criar endpoints  para deposito em varias contas, deletar uma conta, editar dados da conta, editar dados do cliente, pegar todas as contas, pegar todos cliente, delete cliente
}