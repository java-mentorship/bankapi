package br.com.leonardoraupp.apibancaria.infrastructure.entity;

import br.com.leonardoraupp.apibancaria.domain.enums.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Profile profile;
}
