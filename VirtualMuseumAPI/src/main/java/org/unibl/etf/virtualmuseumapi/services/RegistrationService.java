package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.config.ModelMapperConfig;
import org.unibl.etf.virtualmuseumapi.exceptions.BadRequestException;
import org.unibl.etf.virtualmuseumapi.model.dto.RegistrationDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.UserEntity;
import org.unibl.etf.virtualmuseumapi.repositories.UserRepository;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final ModelMapperConfig modelMapper;

    public UserEntity createNewUser(RegistrationDTO registrationDTO) {
        if (userRepository.existsByUsername(registrationDTO.getUserName()))
            throw new BadRequestException("Username is taken.");

        if (userRepository.existsByEmail(registrationDTO.getEmail()))
            throw new BadRequestException("Email is taken.");

        return userRepository.saveAndFlush(modelMapper.modelMapper().map(registrationDTO, UserEntity.class));
    }
}
