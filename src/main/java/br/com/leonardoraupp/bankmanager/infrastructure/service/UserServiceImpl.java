package br.com.leonardoraupp.bankmanager.infrastructure.service;

import br.com.leonardoraupp.bankmanager.application.exception.InvalidUserException;
import br.com.leonardoraupp.bankmanager.application.service.UserService;
import br.com.leonardoraupp.bankmanager.domain.User;
import br.com.leonardoraupp.bankmanager.infrastructure.entity.UserEntity;
import br.com.leonardoraupp.bankmanager.infrastructure.repository.UserRepository;
import br.com.leonardoraupp.bankmanager.utility.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void validateNewUser(User user) throws InvalidUserException {
        if (user == null) {
            throw new InvalidUserException("User is null.");
        }
        if (user.getUsername().isBlank()) {
            throw new InvalidUserException("User username is blank.");
        }
        if (user.getPassword().isBlank()) {
            throw new InvalidUserException("User password is blank");
        }
    }

    @Override
    public User register(User user) throws InvalidUserException {
        validateNewUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s not found.", username));
        }
        UserEntity userEntity = optionalUserEntity.get();
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(userEntity.getProfile().name()));
        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }
}
