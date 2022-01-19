package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.exceptions.NotFoundException;
import org.unibl.etf.virtualmuseumapi.model.entities.MuseumEntity;
import org.unibl.etf.virtualmuseumapi.repositories.MuseumRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MuseumService {

    private final MuseumRepository museumRepository;

    public List<MuseumEntity> getAll() {
        return museumRepository.findAll();
    }

    public MuseumEntity getById(Integer id) {
        return museumRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<MuseumEntity> getAllByNameOrCity(String queryPart) {
        return museumRepository.getAllByNameContainingIgnoreCaseOrCityContainingIgnoreCaseOrderByName(queryPart, queryPart);
    }
}