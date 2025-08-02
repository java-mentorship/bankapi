package br.com.leonardoraupp.apibancaria.domain;

import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime moment;
    private String message;
    private Account account;
    private static DateTimeFormatter brFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Transaction(TransactionType type, BigDecimal amount, String message, Account account) {
        this.type = type;
        this.amount = amount;
        this.moment = LocalDateTime.now();
        this.message = message;
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return type.getName() +
                " de" +
                " " +
                String.format("%.2f", amount) +
                " em" +
                " " +
                moment.format(brFormatter);
    }
}
