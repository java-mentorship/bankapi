package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import br.com.leonardoraupp.apibancaria.domain.Transaction;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "account")
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
    protected List<Transaction> transactions;
}
//  todo: Gerar todas as entidades que precisam serem persistidas.
//   Retirar todos relacionamentos de domain par entity. Mapear todos relacioamentos.
//   Criar mapper.
