package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import br.com.leonardoraupp.apibancaria.domain.Bank;
import br.com.leonardoraupp.apibancaria.domain.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "account")
@AllArgsConstructor
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
    protected List<Transaction> transactions;
    @ManyToOne
    protected Bank bank;
}
//  todo: Gerar todas as entidades que precisam serem persistidas.
//   Retirar todos relacionamentos de domain para entity. Mapear todos relacioamentos.
//   Criar mapper.
