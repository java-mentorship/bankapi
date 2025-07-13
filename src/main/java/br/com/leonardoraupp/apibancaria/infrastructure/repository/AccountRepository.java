package br.com.leonardoraupp.apibancaria.infrastructure.repository;

import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
