package br.com.leonardoraupp.apibancaria.domain;

import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType type;
    private BigDecimal value;
    private LocalDate date;
    private static DateTimeFormatter brFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Transaction(TransactionType type, BigDecimal value, LocalDate date) {
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public Transaction(TransactionType transactionType, double value, LocalDate now) {
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return type.getName() +
                " de" +
                " " +
                String.format("%.2f", value) +
                " em" +
                " " +
                date.format(brFormatter);
    }
}
