package br.com.leonardoraupp.apibancaria.application.request;

import java.time.LocalDate;

public record CreateUserRequest(String username, String password, String profile) {
}
