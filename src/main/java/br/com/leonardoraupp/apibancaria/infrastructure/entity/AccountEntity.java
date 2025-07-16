package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import br.com.leonardoraupp.apibancaria.domain.Bank;
import br.com.leonardoraupp.apibancaria.domain.Transaction;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "account")
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @ManyToOne
    protected UserEntity holder;
    protected Double balance;
    protected Integer agency;
    protected Integer number;
    protected LocalDate openingDate;
    @OneToMany
    protected List<Transaction> transactions;
    @ManyToOne
    protected Bank bank;

    public AccountEntity(UserEntity holder, Double balance, Integer agency, Integer number) {
        this.holder = holder;
        this.balance = balance;
        this.agency = agency;
        this.number = number;
    }
}