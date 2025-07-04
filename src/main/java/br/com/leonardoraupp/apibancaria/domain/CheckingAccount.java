package br.com.leonardoraupp.apibancaria.domain;


import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;

import java.time.LocalDate;
import java.util.List;

public class CheckingAccount extends Account {
    public CheckingAccount(User holder, Integer agency, Integer number) {
        super(holder, agency, number);
    }

    @Override
    public User getHolder() {
        return this.holder;
    }

    @Override
    public Double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(double value) {
        if (value > 0 && value < 1000000.0) {
            this.balance += value;
            addTransaction(new Transaction(TransactionType.DEPOSIT, value, LocalDate.now()));
        } else {
            throw new IllegalArgumentException("Cannot complete the transaction. The deposit value is negative or higher than one million reals.");
        }
    }

    @Override
    public void withdraw(double value) {
        if (this.balance > value) {
            this.balance -= value;
            addTransaction(new Transaction(TransactionType.WITHDRAW, value, LocalDate.now()));
        } else {
            throw new IllegalArgumentException("Cannot complete the transaction. Insufficient balance.");
        }
    }

    @Override
    public void addTransaction(Transaction object) {
        this.transactions.add(object);
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public Integer getAgency() {
        return this.agency;
    }

    @Override
    public Integer getNumber() {
        return this.number;
    }

    @Override
    public LocalDate getDateOpening() {
        return this.openingDate;
    }


}
