package br.com.leonardoraupp.bankmanager.application.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetHolderBalanceResponse(LocalDateTime balanceTimeConsult, BigDecimal balance) {
}
