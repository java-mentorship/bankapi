package br.com.leonardoraupp.apibancaria.application.response;


import java.math.BigDecimal;

public record OpenAccountResponse(Integer id, String firstName, String lastName, BigDecimal balance, Integer agency, Integer number) {
}
