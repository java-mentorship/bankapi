package br.com.leonardoraupp.apibancaria.domain.enums;

public enum Message {
    DEPOSIT_SUCCESSUL_MESSAGE("Deposit effectuated successfully."),
    DEPOSIT_FAILED_MESSAGE("Deposit failed.");

    String description;

    Message(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
