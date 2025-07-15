package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "checking_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckingAccountEntity extends AccountEntity {
}
