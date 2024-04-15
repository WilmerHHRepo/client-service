package com.bootcamp51.microservices.service.impl;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.repository.ClientRepository;
import com.bootcamp51.microservices.service.ClientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ClientServiceImpl implements ClientService{



    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Flux<List<Client>> findAllClient() {
        return Flux.empty(); //clientRepository.findAll();
    }

    @Override
    public Flux<List<Client>> findByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> updateClient(Client client, String id) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> deleteClient(String id) {
        ObjectId objectId = new ObjectId(id);
        Mono<Client> client = findById(id);
        Consumer<String> consumer = clientRepository::deleteById;
        consumer.accept(id);
        return client;
    }
}
