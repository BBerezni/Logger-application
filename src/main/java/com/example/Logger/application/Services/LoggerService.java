package com.example.Logger.application.Services;

import com.example.Logger.application.Model.Logger;
import com.example.Logger.application.Repository.LoggerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerService {

    private final LoggerRepository loggerRepository;

    public LoggerService(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    public List<Logger> findAll() {
        return loggerRepository.findAll();
    }

}
