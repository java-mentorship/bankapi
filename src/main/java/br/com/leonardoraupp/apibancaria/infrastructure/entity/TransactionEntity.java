package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import br.com.leonardoraupp.apibancaria.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_transaction")
@Data
@NoArgsConstructor
public class TransactionEntity {    //TODO: Inserir a propriedade de valor para registrar o valor da transação no banco.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime moment;
    private String message;
    @ManyToOne
    @JoinColumn(name = "account_id") //  foreign key
    private AccountEntity accountEntity;
    @ManyToOne
    @JoinColumn(name = "destination_account_id", nullable = true)
    private AccountEntity destinationAccountEntity;

    public TransactionEntity(TransactionType type, BigDecimal amount, LocalDateTime moment, String message, AccountEntity accountEntity) {
        this.type = type.getName();
        this.amount = amount;
        this.moment = moment;
        this.message = message;
        this.accountEntity = accountEntity;
    }

    public TransactionEntity(TransactionType type, BigDecimal amount, LocalDateTime moment, String message, AccountEntity accountEntity, AccountEntity destinationAccountEntity) {
        this.type = type.getName();
        this.amount = amount;
        this.moment = moment;
        this.message = message;
        this.accountEntity = accountEntity;
        this.destinationAccountEntity = destinationAccountEntity;
    }
}
