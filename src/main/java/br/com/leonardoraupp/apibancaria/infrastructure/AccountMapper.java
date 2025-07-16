package br.com.leonardoraupp.apibancaria.infrastructure;

import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.CheckingAccount;
import br.com.leonardoraupp.apibancaria.domain.User;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;

import java.time.LocalDate;


public abstract class AccountMapper {
    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountEntity(UserMapper.toEntity(account.getHolder()), account.getBalance(), account.getAgency(), account.getNumber());
    }

    public static Account toDomain(AccountEntity account) {
        if (account == null) {
            return null;
        }
        User user = UserMapper.toDomain(account.getHolder());
        return new CheckingAccount(user, account.getAgency(), account.getNumber());
    }
}
