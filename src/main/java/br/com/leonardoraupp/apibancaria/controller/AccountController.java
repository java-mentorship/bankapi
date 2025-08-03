package br.com.leonardoraupp.apibancaria.controller;

import br.com.leonardoraupp.apibancaria.application.AccountDepositUseCase;
import br.com.leonardoraupp.apibancaria.application.AccountWithdrawUseCase;
import br.com.leonardoraupp.apibancaria.application.OpenAccountUseCase;
import br.com.leonardoraupp.apibancaria.application.GetBalanceUseCase;
import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.request.AccountTransactionRequest;
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

    public AccountController(OpenAccountUseCase openAccountUseCase, GetBalanceUseCase getBalanceUseCase, AccountDepositUseCase accountDepositUseCase, AccountWithdrawUseCase accountWithdrawUseCase) {
        this.openAccountUseCase = openAccountUseCase;
        this.getBalanceUseCase = getBalanceUseCase;
        this.accountDepositUseCase = accountDepositUseCase;
        this.accountWithdrawUseCase = accountWithdrawUseCase;
    }

    @PostMapping
    public ResponseEntity<?> openAccount(@RequestBody OpenAccountRequest request) {
        try {
            OpenAccountResponse response = openAccountUseCase.execute(request);
            LOGGER.info("Account created.");
            return ResponseEntity.ok(response);
        } catch (InvalidAccountException e) {
            LOGGER.error("Invalid account data: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InvalidHolderException e) {
            LOGGER.error("Invalid holder data while creating a new account: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Failed to open account: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body("Não foi possível atender a requisição, entre em contato com o suporte.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBalance(@PathVariable Integer id, @RequestBody GetHolderBalanceRequest getBalanceRequest) {
        try {
            GetHolderBalanceResponse response = getBalanceUseCase.execute(id, getBalanceRequest);
            return ResponseEntity.ok(response);
        } catch (InvalidHolderException e) {
            LOGGER.error("Holder informed is invalid: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AccountNotFoundException e) {
            LOGGER.error("Account informed does not exist: {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Integer id, @RequestBody AccountTransactionRequest request) {
        try {
            TransactionResponse response = accountDepositUseCase.execute(id, request);
            return ResponseEntity.ok(response);
        } catch (AccountNotFoundException e) {
            LOGGER.error("Account informed is invalid: {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        } catch (InvalidHolderException e) {
            LOGGER.error("Holder informed is invalid: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/withdraw")
    //TODO: tirar dúvida se é uma boa prática RESTful inserir a para withdraw/deposit após o id para diferenciar os endpoints.
    public ResponseEntity<?> withdraw(@PathVariable Integer id, @RequestBody AccountTransactionRequest request) {
        try {
            TransactionResponse response = accountWithdrawUseCase.execute(id, request);
            return ResponseEntity.ok(response);
        } catch (AccountNotFoundException e) {
            LOGGER.error("Account informed is invalid: {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        } catch (InvalidHolderException e) {
            LOGGER.error("Holder informed is invalid: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO: Criar endpoints  para deposito em varias contas, para deletar uma conta,endpoint para editar dados da conta e cliente.
}
