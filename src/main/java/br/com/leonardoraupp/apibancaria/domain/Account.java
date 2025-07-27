package br.com.leonardoraupp.apibancaria.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public abstract class Account {
    protected Integer id;
    protected Holder holder;
    protected BigDecimal balance;
    protected Integer agency;
    protected Integer number;
    protected LocalDate openingDate;
    protected List<Transaction> transactions = new ArrayList<>();
    private Set<Integer> ids = new LinkedHashSet<>();

    public Account() {
    }

    public Account(Holder holder, Integer agency, Integer number) {
        this.holder = holder;
        this.agency = agency;
        this.number = number;
        this.balance = BigDecimal.ZERO;
        this.openingDate = LocalDate.now();

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public Holder getHolder() {
        return holder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getAgency() {
        return agency;
    }

    public Integer getNumber() {
        return number;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public abstract void deposit(BigDecimal value);

    public abstract void withdraw(BigDecimal value);

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
