package br.com.leonardoraupp.apibancaria.infrastructure;

import br.com.leonardoraupp.apibancaria.domain.User;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.HolderEntity;

public abstract class UserMapper {
    public static HolderEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        return new HolderEntity(user.getName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getBirthDate());
    }
    public static User toDomain(HolderEntity user) {
        if (user == null) {
            return null;
        }
        return new User(user.getName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getBirthDate());
    }
}