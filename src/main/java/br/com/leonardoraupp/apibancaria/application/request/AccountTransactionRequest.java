package br.com.leonardoraupp.apibancaria.application.request;

import java.math.BigDecimal;

public record AccountTransactionRequest(BigDecimal amount, String cpf) {
}
