package org.unibl.etf.virtualmuseumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.virtualmuseumapi.model.entities.ArtifactEntity;

import java.util.List;

@Repository
public interface ArtifactRepository extends JpaRepository<ArtifactEntity, Integer> {

    List<ArtifactEntity> getAllByTourId(Integer tourId);
}
