package com.example.Logger.application.Services;

import com.example.Logger.application.Model.Client;
import com.example.Logger.application.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
