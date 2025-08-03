package br.com.leonardoraupp.apibancaria.utility;

import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.CheckingAccount;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;

public abstract class AccountMapper {
    public static Account toDomain(OpenAccountRequest request) {
        if (request == null) {
            return null;
        }
        return new CheckingAccount(request.firstName(), request.lastName(), request.cpf(),
                request.email(), request.birthDate(), request.agency(), request.number());
    }

    public static Account toDomain(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        Account account = new CheckingAccount(
                HolderMapper.toDomain(accountEntity.getHolder()),
                accountEntity.getAgency(),
                accountEntity.getNumber());
        account.setBalance(accountEntity.getBalance());
        account.setId(accountEntity.getId());
        return account;
    }

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity(HolderMapper.toEntity(account.getHolder()), account.getBalance(), account.getAgency(), account.getNumber());
        accountEntity.setId(account.getId());
        return accountEntity;
    }

    public static OpenAccountResponse toOpenAccountResponse(Account accountDomain) {
        if (accountDomain == null) {
            return null;
        }
        return new OpenAccountResponse(accountDomain.getId(), accountDomain.getHolder().getName(),
                accountDomain.getHolder().getLastName(), accountDomain.getBalance(), accountDomain.getNumber(), accountDomain.getAgency());
    }

//    public static AccountDepositResponse toAccountDepositResponse(Account accountDomain) {
//        if (accountDomain == null) {
//            return null;
//        }
//        return new AccountDepositResponse(accountDomain.getHolder(), accountDomain.getHolder(), "", accountDomain.getBalance(), );
//    }
}
