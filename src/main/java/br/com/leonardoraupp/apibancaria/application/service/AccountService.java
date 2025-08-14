package br.com.leonardoraupp.apibancaria.application.service;

import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.Transaction;

import java.math.BigDecimal;

public interface AccountService {
    Account createAccount(Account account) throws InvalidAccountException, InvalidHolderException;

    Account getAccount(Integer accountId, String cpf) throws InvalidHolderException, AccountNotFoundException;

    Transaction deposit(Integer accountId, BigDecimal amount, String cpf) throws InvalidHolderException, AccountNotFoundException, InvalidAccountException;

    Transaction withdraw(Integer accountId, BigDecimal amount, String cpf) throws AccountNotFoundException, InvalidHolderException, InvalidAccountException;

    Transaction transference(Integer accountId, BigDecimal amount, String cpf, Integer receiverAccountId) throws AccountNotFoundException, InvalidHolderException, InvalidAccountException;
}
