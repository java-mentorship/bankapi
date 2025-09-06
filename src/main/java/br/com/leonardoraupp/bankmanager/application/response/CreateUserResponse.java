package br.com.leonardoraupp.bankmanager.application.response;

import java.time.LocalDateTime;

public record CreateUserResponse(String message, Long id, LocalDateTime createdDate) {
}
