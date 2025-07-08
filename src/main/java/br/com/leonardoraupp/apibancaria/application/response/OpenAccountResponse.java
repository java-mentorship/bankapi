package br.com.leonardoraupp.apibancaria.application.response;


import br.com.leonardoraupp.apibancaria.domain.User;

public record OpenAccountResponse( Integer id, String firstName, String lastName, Double balance,  Integer agency, Integer number) {
}
