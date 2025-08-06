package br.com.leonardoraupp.apibancaria.application.request;

import br.com.leonardoraupp.apibancaria.domain.Holder;

import java.math.BigDecimal;

public record AccountTransactionRequest(BigDecimal amount, String cpf) {
}
