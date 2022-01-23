package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.model.dto.TourInfoDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.TourEntity;
import org.unibl.etf.virtualmuseumapi.services.AuthenticationService;
import org.unibl.etf.virtualmuseumapi.services.TourService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public List<TourInfoDTO> getAll(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @RequestParam(required = false) @Min(1) Integer museumId) {
//        if (authenticationService.credentialsValid(username, password))
//            throw new UnauthorizedException("Invalid credentials.");

        if (museumId != null)
            return tourService.getAllByMuseumId(museumId);

        return tourService.getAll();
    }

    @GetMapping("/{id}")
    public TourEntity getById(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @PathVariable Integer id) {
//        if (authenticationService.credentialsValid(username, password))
//            throw new UnauthorizedException("Invalid credentials.");

        return tourService.getById(id);
    }

    @GetMapping("/current")
    public List<TourInfoDTO> getCurrent(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @RequestParam(required = false) @Min(1) Integer museumId) {
//        if (authenticationService.credentialsValid(username, password))
//            throw new UnauthorizedException("Invalid credentials.");

        if (museumId != null)
            return tourService.getCurrentToursForMuseum(museumId);

        return tourService.getCurrentTours();
    }

    @GetMapping("/upcoming")
    public List<TourInfoDTO> getUpcoming(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @RequestParam(required = false) @Min(1) Integer museumId) {
//        if (authenticationService.credentialsValid(username, password))
//            throw new UnauthorizedException("Invalid credentials.");

        if (museumId != null)
            return tourService.getUpcomingToursForMuseum(museumId);
        return tourService.getUpcomingTours();
    }
}
