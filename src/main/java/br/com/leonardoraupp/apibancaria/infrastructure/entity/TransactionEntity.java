package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import br.com.leonardoraupp.apibancaria.domain.Transaction;
import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @Column(name = "transaction_date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "account_id") //  foreign key
    private AccountEntity accountEntity;

    public TransactionEntity(TransactionType type, LocalDateTime date, AccountEntity accountEntity) {
        this.type = type.getName();
        this.date = date;
        this.accountEntity = accountEntity;
    }
}
