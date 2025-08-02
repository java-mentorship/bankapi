package br.com.leonardoraupp.apibancaria.infrastructure.repository;

import br.com.leonardoraupp.apibancaria.infrastructure.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
