package br.com.leonardoraupp.bankmanager.utility;

import br.com.leonardoraupp.bankmanager.application.request.AuthenticationUserRequest;
import br.com.leonardoraupp.bankmanager.application.request.CreateUserRequest;
import br.com.leonardoraupp.bankmanager.domain.User;
import br.com.leonardoraupp.bankmanager.infrastructure.entity.UserEntity;

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