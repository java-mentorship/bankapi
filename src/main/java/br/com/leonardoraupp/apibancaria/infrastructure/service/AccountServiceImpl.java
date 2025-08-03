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

    //    TODO: Tirar dúvidas se coloco logs nesse método para quando o deposito falhar, quando conseguir realizar. Qual seria a camada mais indicada(controller, serviço, caso de uso).
    @Override
    public Transaction deposit(Integer accountId, BigDecimal amount) throws InvalidHolderException, AccountNotFoundException {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        Account accountDomain = AccountMapper.toDomain(accountEntity.get());
        validateAccount(accountDomain);
        accountDomain.deposit(amount);
        accountRepository.save(AccountMapper.toEntity(accountDomain));
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, Message.DEPOSIT_SUCCESSUL_MESSAGE.getDescription(), accountDomain);
        transactionRepository.save(TransactionMapper.toEntity(transaction));
        return transaction;
    }

    @Override
    public Transaction withdraw(Integer accountId, BigDecimal amount) throws AccountNotFoundException, InvalidHolderException {
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        Account accountDomain = AccountMapper.toDomain(accountEntity.get());
        validateAccount(accountDomain);
        accountDomain.withdraw(amount);
        accountRepository.save(AccountMapper.toEntity(accountDomain));
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount, Message.WITHDRAW_SUCCESSUL_MESSAGE.getDescription(), accountDomain);
        transactionRepository.save(TransactionMapper.toEntity(transaction));
        return transaction;
    }


    private void validateAccount(Account accountDomain) throws InvalidHolderException {
//        TODO: Fazer mais validações para validar operações como depósito, saque, transferência.
        if (accountDomain.getHolder() == null) {
            throw new InvalidHolderException("Holder is null");
        }
    }

    private void validateNewAccount(Account account) throws InvalidAccountException {
        if (account == null) {
            throw new InvalidAccountException("Account is null. It must be informed.");
        }
        if (account.getHolder() == null) {
            throw new InvalidAccountException("Account holder was not informed.");
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
