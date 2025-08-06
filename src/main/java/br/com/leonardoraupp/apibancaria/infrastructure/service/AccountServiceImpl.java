package br.com.leonardoraupp.apibancaria.infrastructure.service;

import br.com.leonardoraupp.apibancaria.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.apibancaria.domain.Transaction;
import br.com.leonardoraupp.apibancaria.domain.enums.Message;
import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.TransactionRepository;
import br.com.leonardoraupp.apibancaria.utility.AccountMapper;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.application.service.HolderService;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.Holder;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.AccountRepository;
import br.com.leonardoraupp.apibancaria.utility.TransactionMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final HolderService holderService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, HolderService holderService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.holderService = holderService;
    }

    @Override
    public Account createAccount(Account account) throws InvalidAccountException, InvalidHolderException {
        validateAccount(account);
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
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        if (!(accountEntity.get().getHolder().getCpf().equals(holderCpf))) {
            throw new InvalidHolderException("Holder cpf informed is invalid: " + holderCpf);
        }
        return AccountMapper.toDomain(accountEntity.get());
    }

    @Override
    public Transaction deposit(Integer accountId, BigDecimal amount, String cpf) throws InvalidHolderException, AccountNotFoundException, InvalidAccountException {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        Account accountDomain = AccountMapper.toDomain(accountEntity.get());
        validateAccount(accountDomain);
        validateHolder(accountDomain, cpf);
        accountDomain.deposit(amount);
        accountRepository.save(AccountMapper.toEntity(accountDomain));
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, Message.DEPOSIT_SUCCESSUL_MESSAGE.getDescription(), accountDomain);
        transactionRepository.save(TransactionMapper.toEntity(transaction));
        return transaction;
    }

    @Override
    public Transaction withdraw(Integer accountId, BigDecimal amount, String cpf) throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        Account accountDomain = AccountMapper.toDomain(accountEntity.get());
        validateAccount(accountDomain);
        validateHolder(accountDomain, cpf);
        accountDomain.withdraw(amount);
        accountRepository.save(AccountMapper.toEntity(accountDomain));
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount, Message.WITHDRAW_SUCCESSUL_MESSAGE.getDescription(), accountDomain);
        transactionRepository.save(TransactionMapper.toEntity(transaction));
        return transaction;
    }

    private void validateHolder(Account account, String cpf) throws InvalidHolderException {
        Optional<Holder> holder = holderService.findHolderByCpf(cpf);
        if (account.getHolder() == null || cpf == null) {
            throw new InvalidHolderException("Holder is null");
        }
        if (holder.isEmpty()) {
            throw new InvalidHolderException("Holder does not exist.");
        }
        if (!account.getHolder().getCpf().equals(holder.get().getCpf())) {
            throw new InvalidHolderException("Holder object and holder from account are different.");
        }
    }

    private void validateAccount(Account account) throws InvalidAccountException {
        if (account == null) {
            throw new InvalidAccountException("Account is null. It must be informed.");
        }
        if (account.getAgency() == null) {
            throw new InvalidAccountException("Number agency was not informed.");
        }
        if (!(String.valueOf(account.getAgency()).length() == 4)) {
            throw new InvalidAccountException("Agency number is invalid. It must be 4 digits.");
        }
        if (!(String.valueOf(account.getNumber()).length() == 5)) {
            throw new InvalidAccountException("Account number informed is invalid it must be 5.");
        }
        if (account.getNumber() == null) {
            throw new InvalidAccountException("Account number is null. It must be informed.");
        }
    }
}
