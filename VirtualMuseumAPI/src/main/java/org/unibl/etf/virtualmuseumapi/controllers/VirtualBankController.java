package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.exceptions.UnauthorizedException;
import org.unibl.etf.virtualmuseumapi.model.dto.PurchasePostDTO;
import org.unibl.etf.virtualmuseumapi.services.AuthenticationService;
import org.unibl.etf.virtualmuseumapi.services.VirtualBankService;

@RestController
@AllArgsConstructor
@RequestMapping("/virtual-bank")
public class VirtualBankController {

    private final VirtualBankService virtualBankService;
    private final AuthenticationService authenticationService;

    @PostMapping
    public Boolean purchaseTicket(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password, @RequestBody @Validated PurchasePostDTO purchaseInfo) {
        if (authenticationService.credentialsValid(username, password))
            throw new UnauthorizedException("Invalid credentials.");

        return virtualBankService.purchaseTicket(purchaseInfo);
    }
}