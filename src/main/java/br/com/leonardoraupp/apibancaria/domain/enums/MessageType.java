package br.com.leonardoraupp.apibancaria.domain.enums;

public enum MessageType {
    DEPOSIT_SUCCESSUL_MESSAGE("Deposit effectuated successfully."),
    DEPOSIT_FAILED_MESSAGE("Deposit failed."),
    WITHDRAW_SUCCESSUL_MESSAGE("Withdraw effectuated successfully."),
    WITHDRAW_FAILED_MESSAGE("Withdraw failed."),
    TRANSFERENCE_SUCCESSFUL_MESSAGE("Transference effectuated successfully."),
    TRANSFERENCE_FAILED_MESSAGE("Transference failed.");

    String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
