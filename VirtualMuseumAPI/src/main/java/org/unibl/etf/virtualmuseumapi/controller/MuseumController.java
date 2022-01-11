package org.unibl.etf.virtualmuseumapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.virtualmuseumapi.model.entity.Museum;
import org.unibl.etf.virtualmuseumapi.service.MuseumService;

@RestController
@AllArgsConstructor
@RequestMapping("/museums")
public class MuseumController {

    private final MuseumService museumService;

    /*
        Validacija i parsiranje.
        ?page=2&size=20
     */
    @GetMapping
    public Page<Museum> getAll(Pageable pageable) {
        return museumService.getAll(pageable);
    }
}
