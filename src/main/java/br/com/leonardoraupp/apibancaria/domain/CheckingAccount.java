package br.com.leonardoraupp.apibancaria.domain;


import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckingAccount extends Account {
    public CheckingAccount(Holder holder, Integer agency, Integer number) {
        super(holder, agency, number);
    }

    public CheckingAccount(String firstName, String lastName, String cpf, String email, LocalDate localDate, Integer agency, Integer number) {
        super(new Holder(firstName, lastName, cpf, email, localDate), agency, number);
    }


    @Override
    public void deposit(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(value);
            addTransaction(new Transaction(TransactionType.DEPOSIT, value, LocalDate.now()));
        } else {
            throw new IllegalArgumentException("Cannot complete the transaction. The deposit value is negative or higher than one million reals.");
        }
    }

    @Override
    public void withdraw(BigDecimal value) {
        if (this.balance.compareTo(value) > 0) {
            this.balance = balance.subtract(value);
            addTransaction(new Transaction(TransactionType.WITHDRAW, value, LocalDate.now()));
        } else {
            throw new IllegalArgumentException("Cannot complete the transaction. Insufficient balance.");
        }
    }
}
