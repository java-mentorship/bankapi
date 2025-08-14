package br.com.leonardoraupp.apibancaria.application.request;

import java.math.BigDecimal;

public record AccountTransferenceTransactionRequest(BigDecimal amount, String cpfReceiver, Integer receiverAccountId) {
}
