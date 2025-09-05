package br.com.leonardoraupp.apibancaria.utility;

import br.com.leonardoraupp.apibancaria.application.request.AuthenticationUserRequest;
import br.com.leonardoraupp.apibancaria.application.request.CreateUserRequest;
import br.com.leonardoraupp.apibancaria.domain.Holder;
import br.com.leonardoraupp.apibancaria.domain.User;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.HolderEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.UserEntity;

import java.time.LocalDateTime;

public abstract class UserMapper {

    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getProfile(), LocalDateTime.now());
    }

    public static User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getProfile(), userEntity.getRegistrationDate());
    }

    public static User toDomain(CreateUserRequest request) {
        if (request == null) {
            return null;
        }
        if (request.username() == null) {
            return null;
        }
        if (request.password() == null) {
            return null;
        }
        if (request.profile() == null) {
            return null;
        }
        return new User(request.username(), request.password(), request.profile());
    }

    public static User toDomain(AuthenticationUserRequest request) {
        if (request == null) {
            return null;
        }
        if (request.username() == null) {
            return null;
        }
        if (request.password() == null) {
            return null;
        }
        return new User(request.username(), request.password());
    }
}