package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.model.entities.CardUserEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.CardUserEntityId;
import org.unibl.etf.virtualmuseumapi.repositories.CardUserRepository;

@Service
@AllArgsConstructor
public class CardUserService {

    private final CardUserRepository cardUserRepository;

    public CardUserEntity getByIdOrElseNull(CardUserEntityId id) { return cardUserRepository.findById(id).orElse(null); }
    public CardUserEntity insertCardUser(CardUserEntity cardUser) { return cardUserRepository.saveAndFlush(cardUser); }
}
