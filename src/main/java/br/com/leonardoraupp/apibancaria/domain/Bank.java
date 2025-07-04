package br.com.leonardoraupp.apibancaria.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Bank {
    private Set<Account> accounts = new LinkedHashSet<>();

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
