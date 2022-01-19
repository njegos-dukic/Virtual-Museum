package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.virtualmuseumapi.model.dto.PurchasePostDTO;
import org.unibl.etf.virtualmuseumapi.services.VirtualBankService;

@RestController
@AllArgsConstructor
@RequestMapping("/virtual-bank")
public class VirtualBankController {
    @Autowired
    private static final VirtualBankService virtualBankService;

    @PostMapping
    public Boolean purchaseTicket(@RequestBody @Validated PurchasePostDTO purchaseInfo) {
        return virtualBankService.purchaseTicket(purchaseInfo);
    }
}
