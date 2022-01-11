package org.unibl.etf.virtualmuseumapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entity.Museum;

import java.util.List;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, Integer> {

    List<Museum> getAllByNameContainingOrderByName(String namePart, Pageable pageable);
}
