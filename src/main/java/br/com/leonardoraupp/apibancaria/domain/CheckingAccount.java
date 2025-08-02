package br.com.leonardoraupp.apibancaria.domain;


import br.com.leonardoraupp.apibancaria.application.request.AccountDepositRequest;
import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CheckingAccount extends Account {
    public CheckingAccount(Holder holder, Integer agency, Integer number) {
        super(holder, agency, number);
    }

    public CheckingAccount(String firstName, String lastName, String cpf, String email, LocalDate localDate, Integer agency, Integer number) {
        super(new Holder(firstName, lastName, cpf, email, localDate), agency, number);
    }

    public CheckingAccount() {
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("1000000")) <= 0) {
            this.balance = this.balance.add(amount);
        } else {
            throw new IllegalArgumentException("Cannot complete the transaction. The deposit amount is negative or higher than one million reals.");
        }
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) > 0) {
            this.balance = balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("Cannot complete the transaction. Insufficient balance.");
        }
    }
}
