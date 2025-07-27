package br.com.leonardoraupp.apibancaria.infrastructure.repository;

import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.HolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HolderRepository extends JpaRepository<HolderEntity, Integer> {
    Optional<HolderEntity> findByCpf(String cpf);
}
