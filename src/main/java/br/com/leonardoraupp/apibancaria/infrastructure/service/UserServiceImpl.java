package br.com.leonardoraupp.apibancaria.infrastructure.service;

import br.com.leonardoraupp.apibancaria.application.service.UserService;
import br.com.leonardoraupp.apibancaria.domain.User;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.UserEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException(String.format("The username %s was not found.", username));
        }
        UserEntity userEntity = byUsername.get();
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(userEntity.getProfile().name()));
        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }
}
