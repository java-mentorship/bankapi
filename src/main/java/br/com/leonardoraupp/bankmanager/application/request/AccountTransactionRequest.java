package br.com.leonardoraupp.bankmanager.application.request;

import java.math.BigDecimal;

public record AccountTransactionRequest(BigDecimal amount, String cpf) {
}
