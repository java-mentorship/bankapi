package br.com.leonardoraupp.apibancaria.application.request;

import java.time.LocalDate;

public record OpenAccountRequest(String firstName, String lastName, String cpf, String email, LocalDate birthDate,
                                 Integer agency, Integer number) {
}
