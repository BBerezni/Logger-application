package com.example.Logger.application.Services;

import com.example.Logger.application.Model.Client;
import com.example.Logger.application.PasswordValidator;
import com.example.Logger.application.Repository.ClientRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.passay.CharacterRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    EmailValidator emailValidator = EmailValidator.getInstance();

    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        if (clientRepository.existsByUsername(client.getUsername()) != 0 || clientRepository.existsByEmail(client.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else if (client.getUsername().length() < 3 || !emailValidator.isValid(client.getEmail()) || PasswordValidator.isValid(client.getPassword()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        client.setId(UUID.randomUUID());
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }




}
