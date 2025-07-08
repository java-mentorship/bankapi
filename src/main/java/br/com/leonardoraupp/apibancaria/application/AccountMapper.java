package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.CheckingAccount;

public final class AccountMapper {
    private AccountMapper() {
    }

    public static Account toEntity(OpenAccountRequest request) {
        if (request == null) {
            return null;
        }
        return new CheckingAccount(request.firstName(), request.lastName(), request.cpf(),
                request.email(), request.birthDate(), request.agency(), request.number());
    }

    public static OpenAccountResponse toDTO(Account entity) {
        if (entity == null) {
            return null;
        }
        return new OpenAccountResponse(entity.getId(), entity.getHolder().getName(),
                entity.getHolder().getLastName(), entity.getBalance(), entity.getNumber(), entity.getAgency());
    }
}
