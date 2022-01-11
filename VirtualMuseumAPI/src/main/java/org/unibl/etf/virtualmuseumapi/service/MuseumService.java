package org.unibl.etf.virtualmuseumapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.model.entity.Museum;
import org.unibl.etf.virtualmuseumapi.repository.MuseumRepository;

@Service
@AllArgsConstructor
public class MuseumService {

    private final MuseumRepository museumRepository;

    public Page<Museum> getAll(Pageable pageable) {
        return museumRepository.findAll(pageable);
    }
}
