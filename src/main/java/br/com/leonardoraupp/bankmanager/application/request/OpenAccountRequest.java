package br.com.leonardoraupp.bankmanager.application.request;

import java.time.LocalDate;

public record OpenAccountRequest(String firstName, String lastName, String cpf, String email, LocalDate birthDate,
                                 Integer agency, Integer number) {
}
