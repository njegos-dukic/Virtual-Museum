package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.model.entities.LogEntity;
import org.unibl.etf.virtualmuseumapi.repositories.LogRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<LogEntity> getAllLogs() {
        return logRepository.findAll();
    }

    public List<LogEntity> getAllLogins() {
        return logRepository.getAllByType("LOGIN");
    }

    public LogEntity insert(LogEntity logEntity) {
        return logRepository.saveAndFlush(logEntity);
    }
}
