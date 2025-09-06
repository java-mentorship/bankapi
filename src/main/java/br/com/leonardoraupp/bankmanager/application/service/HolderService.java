package br.com.leonardoraupp.bankmanager.application.service;

import br.com.leonardoraupp.bankmanager.application.exception.InvalidHolderException;
import br.com.leonardoraupp.bankmanager.domain.Holder;

import java.util.Optional;

public interface HolderService {
    Holder createHolder(Holder holder) throws InvalidHolderException;

    Optional<Holder> findHolderByCpf(String cpf);
}
