package br.com.leonardoraupp.apibancaria.infrastructure;

import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;


public abstract class AccountMapper {
    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountEntity(UserMapper.toEntity(account.getHolder()), account.getBalance(), account.getAgency(), account.getNumber());
    }
}
