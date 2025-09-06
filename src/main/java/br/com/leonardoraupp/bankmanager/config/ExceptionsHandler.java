package br.com.leonardoraupp.bankmanager.config;

import br.com.leonardoraupp.bankmanager.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidAccountException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidUserException;
import br.com.leonardoraupp.bankmanager.config.response.InvalidHolderResponse;
import br.com.leonardoraupp.bankmanager.config.response.InvalidUserResponse;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(InvalidHolderException.class)
    public ResponseEntity<InvalidHolderResponse> handleInvalidHolderException(InvalidHolderException e) {
        LOGGER.error("Holder informed is invalid: {}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(new InvalidHolderResponse(e.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException e) {
        LOGGER.error("Account informed is invalid: {}", e.getMessage(), e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<String> handleInvalidAccountException(InvalidAccountException e) {
        LOGGER.error("Account informed is invalid: {}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        LOGGER.error("Failed to open account: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body("Não foi possível atender a requisição, entre em contato com o suporte.");
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<InvalidUserResponse> handleInvalidHolderException(InvalidUserException e) {
        LOGGER.error("User informed is invalid: {}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(new InvalidUserResponse(e.getMessage()));
    }
}