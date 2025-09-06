package br.com.leonardoraupp.bankmanager.domain;


import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckingAccount extends Account {
    public CheckingAccount(Holder holder, Integer agency, Integer number) {
        super(holder, agency, number);
    }

    public CheckingAccount(String firstName, String lastName, String cpf, String email, LocalDate localDate, Integer agency, Integer number) {
        super(new Holder(firstName, lastName, cpf, email, localDate), agency, number);
    }

    public CheckingAccount() {
    }

    // Implementar validações e exceções
    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("1000000")) <= 0) {
            this.balance = this.balance.add(amount);
        }
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) > 0) {
            this.balance = balance.subtract(amount);
        }
    }

    @Override
    public void transference(BigDecimal amount, Account account) {
        if (this.balance.compareTo(amount) > 0) {
            this.withdraw(amount);
            account.deposit(amount);
        }
    }
}
