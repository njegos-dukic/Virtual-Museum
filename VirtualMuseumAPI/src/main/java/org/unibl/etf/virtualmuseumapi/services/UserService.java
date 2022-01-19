package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.model.entities.UserEntity;
import org.unibl.etf.virtualmuseumapi.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUserByIdOrNull(Integer id) { return userRepository.findById(id).orElse(null); }
}
