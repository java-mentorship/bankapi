package br.com.leonardoraupp.apibancaria.application.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetHolderBalanceResponse(LocalDateTime balanceTimeConsult, BigDecimal balance) {
}
