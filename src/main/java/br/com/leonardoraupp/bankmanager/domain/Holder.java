package br.com.leonardoraupp.bankmanager.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Holder {
    private Integer id;
    private String name;
    private String lastName;
    private String cpf;
    private String email;
    private LocalDate birthDate;
    private Set<Account> accounts = new HashSet<>();
    private User user;


    public Holder(String name, String lastName, String cpf, String email, LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
