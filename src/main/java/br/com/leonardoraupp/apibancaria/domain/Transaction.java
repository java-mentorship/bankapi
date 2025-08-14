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
    private Account destinationAccount;
    private static DateTimeFormatter brFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Transaction(TransactionType type, BigDecimal amount, String message, Account account) {
        this.type = type;
        this.amount = amount;
        this.moment = LocalDateTime.now();
        this.message = message;
        this.account = account;
    }

    public Transaction(TransactionType type, BigDecimal amount, String message, Account account, Account destinationAccount) {
        this.type = type;
        this.amount = amount;
        this.moment = LocalDateTime.now();
        this.message = message;
        this.account = account;
        this.destinationAccount = destinationAccount;
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

    public String getMessage() {
        return message;
    }

    public Account getAccount() {
        return account;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                String.format(", moment= %2f", moment) +
                ", message='" + message + '\'' +
                ", account=" + account +
                ", destinationAccount=" + destinationAccount +
                '}';
    }
}
