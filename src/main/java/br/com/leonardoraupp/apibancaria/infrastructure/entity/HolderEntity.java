package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "holder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private Set<AccountEntity> accounts;

    public HolderEntity(String name, String lastName, String cpf, String email, LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.birthDate = birthDate;
    }
}
