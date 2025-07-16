package br.com.leonardoraupp.apibancaria.infrastructure;

import br.com.leonardoraupp.apibancaria.domain.User;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.UserEntity;

public abstract class UserMapper {
    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserEntity(user.getName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getBirthDate());
    }
    public static User toDomain(UserEntity user) {
        if (user == null) {
            return null;
        }
        return new User(user.getName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getBirthDate());
    }
}