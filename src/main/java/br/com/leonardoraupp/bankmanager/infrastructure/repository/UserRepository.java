package br.com.leonardoraupp.bankmanager.infrastructure.repository;

import br.com.leonardoraupp.bankmanager.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
