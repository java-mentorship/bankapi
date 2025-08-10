package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "holder")
@NoArgsConstructor
@Data
public class HolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String cpf;
    private String email;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "holder")
    private Set<AccountEntity> accounts = new HashSet<>();
    private LocalDateTime registeredDate;
    @OneToOne
    private UserEntity user;

    public HolderEntity(Integer id, String name, String lastName, String cpf, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.birthDate = birthDate;
        this.registeredDate = LocalDateTime.now();
    }
}
