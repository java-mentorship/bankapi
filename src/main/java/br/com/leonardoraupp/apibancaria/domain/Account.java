package br.com.leonardoraupp.apibancaria.domain;

import java.time.LocalDate;
import java.util.*;

public abstract class Account {
    protected Integer id;
    private static Integer generateId = 0;
    protected User holder;
    protected Double balance;
    protected Integer agency;
    protected Integer number;
    protected LocalDate openingDate;
    protected List<Transaction> transactions = new ArrayList<>();
    private Set<Integer> ids = new LinkedHashSet<>();

    public Account() {
    }

    public Account(User holder, Integer agency, Integer number) {
        this.id = generateId++;
        this.holder = holder;
        this.agency = agency;
        this.number = number;
        this.balance = 0.0;
        this.openingDate = LocalDate.now();

    }

    public Integer getId() {
        return id;
    }

    public abstract User getHolder();

    public abstract Double getBalance();

    public abstract void deposit(double value);

    public abstract void withdraw(double value);

    public abstract void addTransaction(Transaction object);

    public abstract List<Transaction> getTransactions();

    public abstract Integer getAgency();

    public abstract Integer getNumber();

    public abstract LocalDate getDateOpening();

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
}
