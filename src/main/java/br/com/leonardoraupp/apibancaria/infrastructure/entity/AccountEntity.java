package br.com.leonardoraupp.apibancaria.infrastructure.entity;

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
    @ManyToOne()
    @JoinColumn(name = "holder_id")
    protected HolderEntity holder;
    protected Double balance;
    protected Integer agency;
    protected Integer number;
    @Column(name = "opening_date")
    protected LocalDate openingDate;
    @OneToMany(mappedBy = "accountEntity")
    protected List<TransactionEntity> transactions;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    protected BankEntity bank;

    public AccountEntity(HolderEntity holder, Double balance, Integer agency, Integer number) {
        this.holder = holder;
        this.balance = balance;
        this.agency = agency;
        this.number = number;
    }
}
