package br.com.leonardoraupp.apibancaria.infrastructure.service;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.infrastructure.AccountMapper;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) throws InvalidAccountException {
        AccountEntity accountEntity = AccountMapper.toEntity(account);
        validateAccount(account);
        accountRepository.save(accountEntity);
        return null;
    }

    private void validateAccount(Account account) throws InvalidAccountException {
        if (account == null) {
            throw new InvalidAccountException("Account not informed.");
        }
        if (account.getHolder() == null) {
            throw new InvalidAccountException("Account holder was not informed.");
        }
        if (account.getAgency() == null) {
            throw new InvalidAccountException("Number agency was not informed");
        }
        if (account.getNumber() == null) {
            throw new InvalidAccountException("Account number was not informed");
        }
//        todo complementar validações de conta e agencia.
    }
}
