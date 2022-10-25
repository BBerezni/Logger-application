package com.example.Logger.application.Controller;

import com.example.Logger.application.Model.Client;
import com.example.Logger.application.Repository.ClientRepository;
import com.example.Logger.application.Security.PasswordValidator;
import com.example.Logger.application.Services.ClientService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {

    private final ClientService clientService;

    private ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/api/client/all")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    EmailValidator emailValidator = EmailValidator.getInstance();

    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        if (clientRepository.existsByUsername(client.getUsername()) != 0 || clientRepository.existsByEmail(client.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else if (client.getUsername().length() < 3 || !emailValidator.isValid(client.getEmail()) || PasswordValidator.isValid(client.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        client.setId(UUID.randomUUID());
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // RADI!!!
//    @PostMapping("/api/clients/login")
//    public ResponseEntity<?> loginClient(@RequestBody String email, String password){
//        if(clientRepository.existsByEmail(email) != 0 || clientRepository.existsByPassword(password) != 0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        var token = UUID.randomUUID();
//        return ResponseEntity.status(HttpStatus.OK).body(token);
//    }

    @PostMapping("/api/clients/login")
    public ResponseEntity<?> loginClient(@RequestBody Client client){
        if(clientRepository.existsByEmail(client.getEmail()) == 0 || clientRepository.existsByPassword(client.getPassword()) == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(client.getId());
    }

}
