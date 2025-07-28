package br.com.leonardoraupp.apibancaria.infrastructure.service;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.service.HolderService;

import br.com.leonardoraupp.apibancaria.domain.Holder;
import br.com.leonardoraupp.apibancaria.utility.HolderMapper;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.HolderEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.HolderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HolderServiceImpl implements HolderService {
    private final HolderRepository holderRepository;

    public HolderServiceImpl(HolderRepository holderRepository) {
        this.holderRepository = holderRepository;
    }

    @Override
    public Holder createHolder(Holder holder) throws InvalidHolderException {
        validateHolder(holder);
        HolderEntity holderEntity = holderRepository.save(HolderMapper.toEntity(holder));
        holder = HolderMapper.toDomain(holderEntity);
        return holder;
    }

    private void validateHolder(Holder holder) throws InvalidHolderException {
        String holderCpf = convertCpf(holder.getCpf());
        if (holder.getName() == null || holder.getName().isBlank()) {
            throw new InvalidHolderException("Holder name is obligatory");
        }
        if (holder.getLastName() == null || holder.getLastName().isBlank()) {
            throw new InvalidHolderException("Holder lastname is obligatory");
        }
        if (holder.getCpf() == null || holder.getCpf().isBlank()) {
            throw new InvalidHolderException("Holder cpf is obligatory");
        }
        if (!holderCpf.equals(11)) {
            throw new InvalidHolderException("Holder cpf is invalid)");
        }
        if (holder.getEmail() == null || holder.getEmail().isBlank()) {
            throw new InvalidHolderException("Holder e-mail is obligatory");
        }
        if (holder.getLastName() == null || holder.getLastName().isEmpty()) {
            throw new InvalidHolderException("Holder birthdate is obligatory");
        }
        Optional<Holder> holderByCpf = findHolderByCpf(holder.getCpf());
        if (holderByCpf.isPresent()) {
            throw new InvalidHolderException("Holder already exists");
        }
    }

    private String convertCpf(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    @Override
    public Optional<Holder> findHolderByCpf(String cpf) {
        Optional<HolderEntity> optHolder = holderRepository.findByCpf(cpf);
        if (optHolder.isPresent()) {
            return Optional.of(HolderMapper.toDomain(optHolder.get()));
        }
        return Optional.empty();
    }
}
