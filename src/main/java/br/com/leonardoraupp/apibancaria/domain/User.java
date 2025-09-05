package br.com.leonardoraupp.apibancaria.domain;

import br.com.leonardoraupp.apibancaria.domain.enums.Profile;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String username;
    private String password;
    private Profile profile;
    private LocalDateTime registrationDate;

    public User(Long id, String username, String password, Profile profile, LocalDateTime registrationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.registrationDate = registrationDate;
    }

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = Profile.valueOf(profile);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profile getProfile() {
        return profile;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
