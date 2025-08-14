package br.com.leonardoraupp.apibancaria.domain.enums;

public enum TransactionType {
    DEPOSIT("Depósito"),
    WITHDRAW("Saque"),
    TRANSFERENCE("Transferência");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}