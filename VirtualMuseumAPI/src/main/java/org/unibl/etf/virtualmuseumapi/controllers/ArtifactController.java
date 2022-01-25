package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.exceptions.UnauthorizedException;
import org.unibl.etf.virtualmuseumapi.model.dto.ArtifactDTO;
import org.unibl.etf.virtualmuseumapi.model.dto.TourRequestDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.LogEntity;
import org.unibl.etf.virtualmuseumapi.services.ArtifactService;
import org.unibl.etf.virtualmuseumapi.services.AuthenticationService;
import org.unibl.etf.virtualmuseumapi.services.LogService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/artifacts")
@AllArgsConstructor
@Validated
public class ArtifactController {

    private final ArtifactService artifactService;
    private final AuthenticationService authenticationService;
    private final LogService logService;

    @PostMapping("/{tourId}")
    public List<ArtifactDTO> getAllArtifactsByTourId(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @PathVariable Integer tourId, @RequestBody TourRequestDTO tourRequestDTO) {
        try {
            String message = username + " tries to access artifacts of tour: " + tourId + " with ticket: " + tourRequestDTO.getTicketNumber() + " and username/password: " + username + "/" + password;
            logService.insert(new LogEntity(0, "ARTIFACTS", message, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while logging action.");
        }

        if (authenticationService.credentialsValid(username, password))
            throw new UnauthorizedException("Invalid credentials.");

        return artifactService.getAllArtifactsByTourId(tourId, tourRequestDTO);
    }
}
