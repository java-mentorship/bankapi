package br.com.leonardoraupp.apibancaria.utility;

import br.com.leonardoraupp.apibancaria.domain.Holder;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.HolderEntity;

public abstract class HolderMapper {

    public static HolderEntity toEntity(Holder user) {
        if (user == null) {
            return null;
        }
        return new HolderEntity(user.getId(), user.getName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getBirthDate());
    }

    public static Holder toDomain(HolderEntity holderEntity) {
        if (holderEntity == null) {
            return null;
        }
        Holder holder = new Holder(
                holderEntity.getName(),
                holderEntity.getLastName(),
                holderEntity.getCpf(),
                holderEntity.getEmail(),
                holderEntity.getBirthDate());
        holder.setId(holderEntity.getId());
        return holder;
    }
}