package com.bootcamp51.microservices.controller;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.repository.ClientRepository;
import com.bootcamp51.microservices.service.ClientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bootcamp51/ms/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<List<Client>> findAllClient(){
        //List<Client> lstClient = clientService.findAllClient();
        return clientService.findAllClient();
    }

    @GetMapping("/by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<List<Client>> findByName(@PathVariable String name){
        //List<Client> lstClient = clientService.findByName(clientRepository, name);
        return clientService.findByName(name);
    }

    @GetMapping("/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Client> findById(@PathVariable String id){
        ObjectId objectId = new ObjectId(id);
        //Optional<Client> client = clientService.findById(clientRepository, objectId);
        return clientService.findById(objectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Client> createClient(@RequestBody Client client){
        //client = clientService.createClient(clientRepository, client);
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client){
        ObjectId objectId =  new ObjectId(id);
        return clientService.updateClient(client, objectId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Client> deleteClient(@PathVariable("id") String id){
        //Optional<Client> client = clientService.deleteClient(clientRepository, id);
        return clientService.deleteClient(id);
    }



}
