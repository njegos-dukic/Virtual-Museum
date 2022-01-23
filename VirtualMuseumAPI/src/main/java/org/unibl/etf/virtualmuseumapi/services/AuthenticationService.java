package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.repositories.UserRepository;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public boolean credentialsValid(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password) == null;
    }
}
