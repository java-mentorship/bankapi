package br.com.leonardoraupp.apibancaria.application;

import br.com.leonardoraupp.apibancaria.application.request.OpenAccountRequest;
import br.com.leonardoraupp.apibancaria.application.response.OpenAccountResponse;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.CheckingAccount;

public abstract class AccountMapper {
    public static Account toDomain(OpenAccountRequest request) {
        if (request == null) {
            return null;
        }
        return new CheckingAccount(request.firstName(), request.lastName(), request.cpf(),
                request.email(), request.birthDate(), request.agency(), request.number());
    }

    public static OpenAccountResponse toAccountDTO(Account domain) {
        if (domain == null) {
            return null;
        }
        return new OpenAccountResponse(domain.getId(), domain.getHolder().getName(),
                domain.getHolder().getLastName(), domain.getBalance(), domain.getNumber(), domain.getAgency());
    }
}
