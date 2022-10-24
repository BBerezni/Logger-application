package com.example.Logger.application.Controller;

import com.example.Logger.application.Model.Client;
import com.example.Logger.application.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/api/client/all")
    public List<Client> findAll(){
        return clientService.findAll();
    }

    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client){
        return clientService.registerClient(client);
    }


}
