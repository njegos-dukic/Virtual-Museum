package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.MuseumEntity;

import java.util.List;

@Repository
public interface MuseumRepository extends JpaRepository<MuseumEntity, Integer> {

   List<MuseumEntity> getAllByNameContainingIgnoreCaseOrCityContainingIgnoreCaseOrderByName(String namePart, String cityPart);
}