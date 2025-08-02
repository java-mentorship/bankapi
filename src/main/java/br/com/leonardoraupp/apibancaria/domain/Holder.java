package br.com.leonardoraupp.apibancaria.domain;

import java.time.LocalDate;

public class Holder {
    private Integer id;
    private String name;
    private String lastName;
    private String cpf;
    private String email;
    private LocalDate birthDate;

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

    public void setId(Integer id) {
        this.id = id;
    }
}
