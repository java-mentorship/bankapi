package br.com.leonardoraupp.bankmanager.domain.enums;

public enum Profile {
    ADMIN("ADMIN"),
    USER("USER");

    private final String description;

    Profile(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
