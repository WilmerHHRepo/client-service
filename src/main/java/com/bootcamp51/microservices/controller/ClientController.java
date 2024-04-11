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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bootcamp51/ms/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;
//
//    @GetMapping("/status")
//    public ResponseEntity<String> getStatus(){
//        String OK = "OK";
//        return new ResponseEntity<>(OK, HttpStatus.OK);
//
//    }

    @GetMapping
    public ResponseEntity<List<Client>> findAllClient(){
        List<Client> lstClient = clientService.findAllClient();
        return new ResponseEntity<List<Client>>(lstClient, HttpStatus.OK);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<Client>> findByName(@PathVariable String name){
        List<Client> lstClient = clientService.findByName(clientRepository, name);
        return new ResponseEntity<List<Client>>(lstClient, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id){
        Client client = clientService.findById(clientRepository, id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        client = clientService.createClient(clientRepository, client);
        return  new ResponseEntity<>(client, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client){
        ObjectId objectId = new ObjectId(id);
        client.setId(objectId);
        return new ResponseEntity<Client>(client, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") String id){
        Client client = clientService.deleteClient(clientRepository, id);
        return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
    }



}
