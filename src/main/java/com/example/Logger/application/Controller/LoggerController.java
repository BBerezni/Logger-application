package com.example.Logger.application.Controller;

import com.example.Logger.application.Model.Logger;
import com.example.Logger.application.Model.logType;
import com.example.Logger.application.Repository.ClientRepository;
import com.example.Logger.application.Repository.LoggerRepository;
import com.example.Logger.application.Services.ClientService;
import com.example.Logger.application.Services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class LoggerController {

    private final LoggerService loggerService;
    private LoggerRepository loggerRepository;
    private final ClientService clientService;
    private ClientRepository clientRepository;

    @Autowired
    public LoggerController(LoggerService loggerService, LoggerRepository loggerRepository, ClientService clientService, ClientRepository clientRepository) {
        this.loggerService = loggerService;
        this.loggerRepository = loggerRepository;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/api/logs/all")
    public List<Logger> findAll() {
        return loggerRepository.findAll();
    }

    @PostMapping("/api/logs/create")
    public ResponseEntity<Void> createLog(@RequestHeader("clientId") UUID id, @RequestBody Logger logger){

        if(logger.getLogType().getValue() != logType.ERROR.getValue() &&
                logger.getLogType().getValue() != logType.WARNING.getValue() &&
                logger.getLogType().getValue() != logType.INFO.getValue()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if(logger.getMessage().length() > 1024){
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(null);
        } else if (clientRepository.findId(id) == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        logger.setCreatedDate(LocalDate.now());
        loggerRepository.save(logger);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/api/logs/search")
    public ResponseEntity<List> searchLogs(@RequestHeader("clientId") UUID id,
                                                   @RequestParam(value = "dateFrom", required = false) String dateFrom,
                                                   @RequestParam(value = "dateTo", required = false) String dateTo,
                                                   @RequestParam(value = "logType", required = false) logType logType,
                                                   @RequestBody Logger logger){
        if(dateFrom == null && dateTo == null && logger.getLogType().getValue() != com.example.Logger.application.Model.logType.ERROR.getValue() &&
                        logger.getLogType().getValue() != com.example.Logger.application.Model.logType.WARNING.getValue() &&
                        logger.getLogType().getValue() != com.example.Logger.application.Model.logType.INFO.getValue()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (clientRepository.findId(id) == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
//        ;
        return ResponseEntity.status(HttpStatus.OK).body(loggerRepository.findLogsFromTo(dateFrom, dateTo, logType));
    }
}
