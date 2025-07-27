package br.com.leonardoraupp.apibancaria.application.service;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.request.CreateHolderRequest;
import br.com.leonardoraupp.apibancaria.domain.Holder;

import java.util.Optional;

public interface HolderService {
    Holder createHolder(Holder holder) throws InvalidHolderException;

    Optional<Holder> findHolderByCpf(String cpf);
}
