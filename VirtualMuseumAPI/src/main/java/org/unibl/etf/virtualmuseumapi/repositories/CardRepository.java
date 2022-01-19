package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {
}
