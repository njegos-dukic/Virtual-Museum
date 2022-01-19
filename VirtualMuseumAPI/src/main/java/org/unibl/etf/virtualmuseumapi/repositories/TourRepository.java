package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.TourEntity;

import java.time.Instant;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Integer> {

    List<TourEntity> getAllByMuseumMuseumId(Integer museumId);
    List<TourEntity> getAllByStartAfter(Instant dateTime);
    List<TourEntity> getAllByStartAfterAndMuseumMuseumId(Instant dateTime, Integer museumId);
}
