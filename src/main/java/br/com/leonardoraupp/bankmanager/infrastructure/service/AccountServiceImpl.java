package br.com.leonardoraupp.bankmanager.infrastructure.service;

import br.com.leonardoraupp.bankmanager.application.exception.AccountNotFoundException;
import br.com.leonardoraupp.bankmanager.domain.Transaction;
import br.com.leonardoraupp.bankmanager.domain.enums.MessageType;
import br.com.leonardoraupp.bankmanager.domain.enums.TransactionType;
import br.com.leonardoraupp.bankmanager.infrastructure.repository.TransactionRepository;
import br.com.leonardoraupp.bankmanager.utility.AccountMapper;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidAccountException;
import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.application.service.AccountService;
import br.com.leonardoraupp.bankmanager.application.service.HolderService;
import br.com.leonardoraupp.bankmanager.domain.Account;
import br.com.leonardoraupp.bankmanager.domain.Holder;
import br.com.leonardoraupp.bankmanager.infrastructure.entity.AccountEntity;
import br.com.leonardoraupp.bankmanager.infrastructure.repository.AccountRepository;
import br.com.leonardoraupp.bankmanager.utility.TransactionMapper;
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
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, MessageType.DEPOSIT_SUCCESSUL_MESSAGE.getDescription(), accountDomain);
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
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount, MessageType.WITHDRAW_SUCCESSUL_MESSAGE.getDescription(), accountDomain);
        transactionRepository.save(TransactionMapper.toEntity(transaction));
        return transaction;
    }

    @Override
    public Transaction transference(Integer accountId, BigDecimal amount, String receiverCpf, Integer receiverAccountId) throws AccountNotFoundException, InvalidHolderException, InvalidAccountException {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        Optional<AccountEntity> receiverAccountEntity = accountRepository.findById(receiverAccountId);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        if (receiverAccountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + receiverAccountId);
        }
        Optional<Holder> receiverHolder = holderService.findHolderByCpf(receiverCpf);
        if (receiverHolder.isEmpty()) {
            throw new InvalidHolderException("Holder does not exist.");
        }
        Account account = AccountMapper.toDomain(accountEntity.get());
        validateAccount(account);
        validateHolder(account, account.getHolder().getCpf());
        Account receiverAccount = AccountMapper.toDomain(receiverAccountEntity.get());
        validateAccount(receiverAccount);
        validateHolder(receiverAccount, receiverCpf);
        account.transference(amount, receiverAccount);
        accountRepository.save(AccountMapper.toEntity(account));
        accountRepository.save(AccountMapper.toEntity(receiverAccount));
        Transaction transaction = new Transaction(TransactionType.TRANSFERENCE, amount, MessageType.TRANSFERENCE_SUCCESSFUL_MESSAGE.getDescription(), account, receiverAccount);
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
