package com.bootcamp51.microservices.controller;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.service.ClientService;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class ClientController controller.
 * author by Wilmer Huaqui.
 */
@RestController
@RequestMapping("")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<Client> findAllClient() {
    return clientService.findAllClient();
  }

  @GetMapping("/by-name/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Flux<List<Client>> findByName(@PathVariable String name) {
    return clientService.findByName(name);
  }

  @GetMapping("/by-cta/{account}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Client> findByNumAccount(@PathVariable String account) {
    return clientService.findByNumAccount(account);
  }

  @GetMapping("/by-document/{document}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Client> findByNumDocument(@PathVariable String document) {
    return clientService.findByNumDocument(document);
  }

  @GetMapping("/by-id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Client> findById(@PathVariable String id) {
    ObjectId objectId = new ObjectId(id);
    return clientService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Client> createClient(@RequestBody Client client) {
    return clientService.createClient(client);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
  public Mono<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
    ObjectId objectId = new ObjectId(id);
    return clientService.createClient(client);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<Client> deleteClient(@PathVariable("id") String id) {
    return clientService.deleteClient(id);
  }


}
