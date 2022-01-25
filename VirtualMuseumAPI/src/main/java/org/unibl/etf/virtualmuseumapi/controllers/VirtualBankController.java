package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.exceptions.UnauthorizedException;
import org.unibl.etf.virtualmuseumapi.model.dto.PurchasePostDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.LogEntity;
import org.unibl.etf.virtualmuseumapi.services.AuthenticationService;
import org.unibl.etf.virtualmuseumapi.services.LogService;
import org.unibl.etf.virtualmuseumapi.services.VirtualBankService;

import java.time.Instant;

@RestController
@AllArgsConstructor
@RequestMapping("/virtual-bank")
public class VirtualBankController {

    private final VirtualBankService virtualBankService;
    private final AuthenticationService authenticationService;
    private final LogService logService;

    @PostMapping
    public Boolean purchaseTicket(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @RequestBody @Validated PurchasePostDTO purchaseInfo) {
        try {
            String message = username + " tries to purchase card with following details: " + purchaseInfo;
            logService.insert(new LogEntity(0, "PURCHASE", message, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while logging action.");
        }

        if (authenticationService.credentialsValid(username, password))
            throw new UnauthorizedException("Invalid credentials.");

        return virtualBankService.purchaseTicket(purchaseInfo);
    }
}