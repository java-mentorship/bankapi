package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @ManyToOne
    @JoinColumn(name = "holder_id")
    protected HolderEntity holder;
    protected BigDecimal balance;
    protected Integer agency;
    protected Integer number;
    @Column(name = "opening_date")
    protected LocalDate openingDate;

    public AccountEntity(HolderEntity holder, BigDecimal balance, Integer agency, Integer number) {
        this.holder = holder;
        this.balance = balance;
        this.agency = agency;
        this.number = number;
        this.openingDate = LocalDate.now();
    }

    public AccountEntity(BigDecimal balance, Integer agency, Integer number) {
        this.balance = balance;
        this.agency = agency;
        this.number = number;
    }
}
