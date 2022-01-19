package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.CardUserEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.CardUserEntityId;

@Repository
public interface CardUserRepository extends JpaRepository<CardUserEntity, CardUserEntityId> {
}
