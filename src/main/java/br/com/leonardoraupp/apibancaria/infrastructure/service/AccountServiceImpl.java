package br.com.leonardoraupp.apibancaria.infrastructure.service;

import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.utility.AccountMapper;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.application.service.HolderService;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.Holder;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final HolderService holderService;

    public AccountServiceImpl(AccountRepository accountRepository, HolderService holderService) {
        this.accountRepository = accountRepository;
        this.holderService = holderService;
    }

    @Override
    public Account createAccount(Account account) throws InvalidAccountException, InvalidHolderException {
        validateNewAccount(account);
        Optional<Holder> optionalUser = holderService.findHolderByCpf(account.getHolder().getCpf());
        if (optionalUser.isPresent()) {
            account.setHolder(optionalUser.get());
        } else {
            Holder holder = holderService.createHolder(account.getHolder());
            account.setHolder(holder);
        }
        AccountEntity accountEntity = AccountMapper.toEntity(account);
        accountRepository.save(accountEntity);
        return AccountMapper.toDomain(accountEntity);
    }

    @Override
    public Account getAccount(Integer accountId, String holderCpf) throws InvalidHolderException, AccountNotFoundException {
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);
        if (optionalAccountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        AccountEntity accountEntity = optionalAccountEntity.get();
        if (!(accountEntity.getHolder().getCpf().equals(holderCpf))) {
            throw new InvalidHolderException("Holder cpf informed is invalid: " + holderCpf);
        }
        return AccountMapper.toDomain(accountEntity);
    }

    private void validateNewAccount(Account account) throws InvalidAccountException {
        if (account == null) {
            throw new InvalidAccountException("Account not informed.");
        }
        if (account.getHolder() == null) {
            throw new InvalidAccountException("Account holder was not informed.");
        }
        if (account.getAgency() == null) {
            throw new InvalidAccountException("Number agency was not informed.");
        }
        if (!(account.getAgency().equals(4))) {
            throw new InvalidAccountException("Number agency informed is invalid.");
        }
        if (!(account.getNumber().equals(5))) {
            throw new InvalidAccountException("Number agency informed is invalid.");
        }
        if (account.getNumber() == null) {
            throw new InvalidAccountException("Account number was not informed.");
        }
    }
}
