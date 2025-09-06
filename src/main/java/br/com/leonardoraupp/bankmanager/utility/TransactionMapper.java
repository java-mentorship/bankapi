package br.com.leonardoraupp.bankmanager.utility;

import br.com.leonardoraupp.bankmanager.application.response.TransactionResponse;
import br.com.leonardoraupp.bankmanager.domain.Transaction;
import br.com.leonardoraupp.bankmanager.infrastructure.entity.TransactionEntity;

public abstract class TransactionMapper {
    public static TransactionResponse toTransactionResponse(Transaction deposit) throws NullPointerException {
        if (deposit == null) {
            throw new NullPointerException("Transaction is null");
        }
        return new TransactionResponse(deposit);
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) {
            throw new NullPointerException("Transaction is null.");
        }
        if (transaction.getDestinationAccount() != null) {
            return new TransactionEntity(transaction.getType(), transaction.getAmount(), transaction.getMoment(),
                    transaction.getMessage(), AccountMapper.toEntity(transaction.getAccount()), AccountMapper.toEntity(transaction.getDestinationAccount()));
        }
        return new TransactionEntity(transaction.getType(), transaction.getAmount(), transaction.getMoment(),
                transaction.getMessage(), AccountMapper.toEntity(transaction.getAccount()));
    }
}
