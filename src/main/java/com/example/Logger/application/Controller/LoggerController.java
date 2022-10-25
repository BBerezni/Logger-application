package com.example.Logger.application.Controller;

import com.example.Logger.application.Repository.LoggerRepository;
import com.example.Logger.application.Services.LoggerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {

    private final LoggerService loggerService;

    private LoggerRepository loggerRepository;

    public LoggerController(LoggerService loggerService, LoggerRepository loggerRepository) {
        this.loggerService = loggerService;
        this.loggerRepository = loggerRepository;
    }


}
