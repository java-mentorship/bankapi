package br.com.leonardoraupp.bankmanager.infrastructure.repository;

import br.com.leonardoraupp.bankmanager.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
