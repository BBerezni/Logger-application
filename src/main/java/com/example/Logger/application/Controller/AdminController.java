package com.example.Logger.application.Controller;

import com.example.Logger.application.Model.Client;
import com.example.Logger.application.Model.UserAuth;
import com.example.Logger.application.Repository.AdminRepository;
import com.example.Logger.application.Repository.ClientRepository;
import com.example.Logger.application.Repository.LoggerRepository;
import com.example.Logger.application.Security.PasswordValidator;
import com.example.Logger.application.Services.AdminService;
import com.example.Logger.application.Services.ClientService;
import com.example.Logger.application.Services.LoggerService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AdminController {

    private final ClientService clientService;

    private ClientRepository clientRepository;

    private final AdminService adminService;

    private AdminRepository adminRepository;

    private final LoggerRepository loggerRepository;

    private LoggerService loggerService;

    @Autowired
    public AdminController(ClientService clientService, ClientRepository clientRepository,
                           AdminService adminService, AdminRepository adminRepository,
                           LoggerRepository loggerRepository, LoggerService loggerService) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.loggerRepository = loggerRepository;
        this.loggerService = loggerService;
    }

    EmailValidator emailValidator = EmailValidator.getInstance();

    @PostMapping("/api/admin/register")
    public ResponseEntity<Void> registerAdmin(@RequestBody Client client) {
        if (clientRepository.existsByUsername(client.getUsername()) != 0 || clientRepository.existsByEmail(client.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else if (client.getUsername().length() < 3 || !emailValidator.isValid(client.getEmail()) || PasswordValidator.isValid(client.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        client.setUserAuth(UserAuth.ADMIN);
        client.setId(UUID.randomUUID());
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<Void> changePassword(@RequestHeader("adminId") UUID idAdmin, @RequestBody Client client,
                                               @PathVariable(value = "clientId") UUID clientId){
        if(idAdmin.equals(client.getId()) && clientRepository.findId(idAdmin) == 0){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else if (!idAdmin.equals(client.getId()) && clientRepository.findId(idAdmin) == 0){
            return ResponseEntity.status((HttpStatus.FORBIDDEN)).body((null));
        } else if(clientRepository.findId(clientId) == 0){
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body((null));
        }
        String psw = client.getPassword();
        clientRepository.upgradeClientPassword(clientId, psw);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/api/clients")
    public ResponseEntity<?> getAllClients(@RequestHeader("adminId") UUID idAdmin){
        if(clientRepository.getAdmin(idAdmin) == 0){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else if (clientRepository.getAdmin(idAdmin) == 0){
            return ResponseEntity.status((HttpStatus.FORBIDDEN)).body((null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }
}
