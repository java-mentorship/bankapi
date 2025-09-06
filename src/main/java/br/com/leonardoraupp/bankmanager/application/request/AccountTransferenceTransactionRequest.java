package br.com.leonardoraupp.bankmanager.application.request;

import java.math.BigDecimal;

public record AccountTransferenceTransactionRequest(BigDecimal amount, String cpfReceiver, Integer receiverAccountId) {
}
