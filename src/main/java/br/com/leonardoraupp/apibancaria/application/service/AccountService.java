package br.com.leonardoraupp.apibancaria.application.service;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.domain.Account;

public interface AccountService {
    Account createAccount(Account account) throws InvalidAccountException;
}
