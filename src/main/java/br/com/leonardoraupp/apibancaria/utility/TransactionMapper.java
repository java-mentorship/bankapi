package br.com.leonardoraupp.apibancaria.utility;

import br.com.leonardoraupp.apibancaria.application.response.TransactionResponse;
import br.com.leonardoraupp.apibancaria.domain.Transaction;
import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.TransactionEntity;

public class TransactionMapper {
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
        return new TransactionEntity(transaction.getType(), transaction.getMoment(), AccountMapper.toEntity(transaction.getAccount()));
    }
}
