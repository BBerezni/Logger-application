package com.example.Logger.application.Services;

import com.example.Logger.application.Repository.LoggerRepository;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    private final LoggerRepository loggerRepository;

    public LoggerService(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }
}
