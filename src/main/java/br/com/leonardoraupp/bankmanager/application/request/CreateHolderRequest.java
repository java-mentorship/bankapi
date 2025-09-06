package br.com.leonardoraupp.bankmanager.application.request;

import java.time.LocalDate;

public record CreateHolderRequest(String name, String lastName, String cpf, String email, LocalDate birthDate) {
}
