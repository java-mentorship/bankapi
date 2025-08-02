package br.com.leonardoraupp.apibancaria.application.service;

import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.Transaction;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account) throws InvalidAccountException, InvalidHolderException;

    Account getAccount(Integer accountId, String holderCpf) throws InvalidHolderException, AccountNotFoundException;

    Transaction deposit(Integer accountId, BigDecimal amount) throws InvalidHolderException, AccountNotFoundException;
}
