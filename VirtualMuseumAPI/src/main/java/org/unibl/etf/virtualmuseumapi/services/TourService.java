package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.config.ModelMapperConfig;
import org.unibl.etf.virtualmuseumapi.exceptions.BadRequestException;
import org.unibl.etf.virtualmuseumapi.exceptions.NotFoundException;
import org.unibl.etf.virtualmuseumapi.model.dto.TourInfoDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.TourEntity;
import org.unibl.etf.virtualmuseumapi.repositories.TourRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final ModelMapperConfig modelMapper;

    public List<TourInfoDTO> getAll() {
        return tourRepository.findAll().stream()
                                       .map(t -> modelMapper.modelMapper().map(t, TourInfoDTO.class))
                                       .collect(Collectors.toList());
    }

    public List<TourInfoDTO> getAllByMuseumId(Integer id) {
        return tourRepository.getAllByMuseumMuseumId(id)
                .stream()
                .map(t -> modelMapper.modelMapper().map(t, TourInfoDTO.class))
                .collect(Collectors.toList());
    }

    public TourEntity getById(Integer id) {
        return tourRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public TourEntity getByIdOrNull(Integer id) {
        return tourRepository.findById(id).orElse(null);
    }

    public List<TourInfoDTO> getUpcomingTours() {
        return tourRepository.getAllByStartAfter(Instant.now()).stream()
                                                               .map(t -> modelMapper.modelMapper().map(t, TourInfoDTO.class))
                                                               .collect(Collectors.toList());
    }

    public List<TourInfoDTO> getUpcomingToursForMuseum(Integer museumId) {
        return tourRepository.getAllByStartAfterAndMuseumMuseumId(Instant.now(), museumId).stream()
                .map(t -> modelMapper.modelMapper().map(t, TourInfoDTO.class))
                .collect(Collectors.toList());
    }

    public List<TourInfoDTO> getCurrentTours() {
        return tourRepository.findAll().stream()
                                       .filter(t -> t.getStart().isBefore(Instant.now()) && t.getStart().plus((long) (t.getDuration() * 60), ChronoUnit.MINUTES).isAfter(Instant.now()))
                                       .map(t -> modelMapper.modelMapper().map(t, TourInfoDTO.class))
                                       .collect(Collectors.toList());
    }

    public List<TourInfoDTO> getCurrentToursForMuseum(Integer museumId) {
        return tourRepository.findAll().stream()
                .filter(t -> t.getMuseum().getMuseumId().equals(museumId) && t.getStart().isBefore(Instant.now()) && t.getStart().plus((long) (t.getDuration() * 60), ChronoUnit.MINUTES).isAfter(Instant.now()))
                .map(t -> modelMapper.modelMapper().map(t, TourInfoDTO.class))
                .collect(Collectors.toList());
    }

    public Double getTourPriceById(Integer id) { return tourRepository.getById(id).getPrice(); }
}