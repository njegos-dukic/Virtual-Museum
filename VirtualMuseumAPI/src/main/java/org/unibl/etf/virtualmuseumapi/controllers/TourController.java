package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.model.dto.TourInfoDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.TourEntity;
import org.unibl.etf.virtualmuseumapi.services.TourService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;

    @GetMapping
    public List<TourInfoDTO> getAll(@RequestParam(required = false) @Min(1) Integer museumId) {
        if (museumId != null)
            return tourService.getAllByMuseumId(museumId);

        return tourService.getAll();
    }

    @GetMapping("/{id}")
    public TourEntity getById(@PathVariable Integer id) {
        return tourService.getById(id);
    }

    @GetMapping("/current")
    public List<TourInfoDTO> getCurrent(@RequestParam(required = false) @Min(1) Integer museumId) {
        if (museumId != null)
            return tourService.getCurrentToursForMuseum(museumId);

        return tourService.getCurrentTours();
    }

    @GetMapping("/upcoming")
    public List<TourInfoDTO> getUpcoming(@RequestParam(required = false) @Min(1) Integer museumId) {
        if (museumId != null)
            return tourService.getUpcomingToursForMuseum(museumId);
        return tourService.getUpcomingTours();
    }
}
