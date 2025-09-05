package br.com.leonardoraupp.apibancaria.application.response;

import java.time.LocalDateTime;

public record CreateUserResponse(String message, Long id, LocalDateTime createdDate) {
}
