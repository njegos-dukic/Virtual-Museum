package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.model.entities.MuseumEntity;
import org.unibl.etf.virtualmuseumapi.services.MuseumService;

import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/museums")
public class MuseumController {

    private final MuseumService museumService;

    @GetMapping
    public List<MuseumEntity> getAll(@RequestParam(required = false) @Size(min = 3, message = "Query parameter must be at least 3 characters long.") String query) {
        if (query != null)
            return museumService.getAllByNameOrCity(query);

        return museumService.getAll();
    }

    @GetMapping("/{id}")
    public MuseumEntity getById(@PathVariable Integer id) {
        return museumService.getById(id);
    }
}
