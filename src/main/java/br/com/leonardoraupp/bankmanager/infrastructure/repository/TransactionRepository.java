package br.com.leonardoraupp.bankmanager.infrastructure.repository;

import br.com.leonardoraupp.bankmanager.infrastructure.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
