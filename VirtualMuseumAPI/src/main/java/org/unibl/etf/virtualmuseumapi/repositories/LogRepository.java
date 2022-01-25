package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.LogEntity;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Integer> {

    List<LogEntity> getAllByType(String type);
}
