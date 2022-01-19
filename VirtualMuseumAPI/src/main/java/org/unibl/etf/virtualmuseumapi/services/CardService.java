package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.model.entities.CardEntity;
import org.unibl.etf.virtualmuseumapi.repositories.CardRepository;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardEntity getByCardNumberOrElseNull(String cardNumber) {
        return cardRepository.findById(cardNumber).orElse(null);
    }

    public CardEntity updateCardEntity(CardEntity cardEntity) {
        return cardRepository.saveAndFlush(cardEntity);
    }
}
