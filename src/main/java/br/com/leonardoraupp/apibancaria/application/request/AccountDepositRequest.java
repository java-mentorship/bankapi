package br.com.leonardoraupp.apibancaria.application.request;

import br.com.leonardoraupp.apibancaria.domain.Account;

import java.math.BigDecimal;

public record AccountDepositRequest(BigDecimal amount) {
}
