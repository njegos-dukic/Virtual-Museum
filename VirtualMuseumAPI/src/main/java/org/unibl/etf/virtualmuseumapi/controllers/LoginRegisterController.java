package org.unibl.etf.virtualmuseumapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualmuseumapi.model.dto.RegistrationDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.LogEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.UserEntity;
import org.unibl.etf.virtualmuseumapi.services.AuthenticationService;
import org.unibl.etf.virtualmuseumapi.services.LogService;
import org.unibl.etf.virtualmuseumapi.services.RegistrationService;

import java.time.Instant;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class LoginRegisterController {

    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final LogService logService;

    @GetMapping("/login")
    public UserEntity login(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password) {
        try {
            String message = username + " tries to log in with password: " + password;
            logService.insert(new LogEntity(0, "LOGIN-TRY", message, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while logging action.");
        }

        return authenticationService.loginUser(username, password);
    }

    @GetMapping("/logout")
    public boolean logout(@RequestHeader(value = "clientId", required = false) String username, @RequestHeader(value = "clientPassword", required = false) String password) {
        try {
            String message = username + " logs out";
            logService.insert(new LogEntity(0, "LOGOUT", message, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while logging action.");
        }

        return authenticationService.logoutUser(username, password);
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody @Validated RegistrationDTO registrationInfo) {
        try {
            String message = "Registration: " + registrationInfo;
            logService.insert(new LogEntity(0, "REGISTRATION", message, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while logging action.");
        }

        return registrationService.createNewUser(registrationInfo);
    }
}