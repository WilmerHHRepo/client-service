package com.bootcamp51.microservices.service.impl;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.repository.ClientRepository;
import com.bootcamp51.microservices.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;

/**
 * Class ClientServiceImpl Service implements.
 * author by Wilmer H.
 */
@Service
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  /**
   * All client.
   *
   * @return Client.
   */
  @Override
  public Flux<Client> findAllClient() {
    return clientRepository.findAll();
  }

  /**
   * All client for name.
   *
   * @param name Param input.
   * @return List Client.
   */
  @Override
  public Flux<List<Client>> findByName(String name) {
    return clientRepository.findByName(name);
  }

  /**
   * Client for number document.
   *
   * @param numAccount Param input.
   * @return Client.
   */
  @Override
  public Mono<Client> findByNumAccount(String numAccount) {
    return clientRepository.findByNumAccount(numAccount);
  }

  /**
   * Client for number document.
   *
   * @param numDocument Param input.
   * @return Client.
   */
  @Override
  public Mono<Client> findByNumDocument(String numDocument) {
    return clientRepository.findByNumDocument(numDocument);
  }

  /**
   * Client for id.
   *
   * @param id Param input.
   * @return Client.
   */
  @Override
  public Mono<Client> findById(String id) {
    return clientRepository.findById(id);
  }

  /**
   * Create client.
   *
   * @param client Param input.
   * @return Client.
   */
  @Override
  public Mono<Client> createClient(Client client) {
    Optional.ofNullable(client).ifPresent(c -> {

      switch (c.getIndTypeClient()){
        case "3":

          break;
        case "4":

          break;
      }


    });










    return clientRepository.save(client);
  }

  /**
   * Update client.
   *
   * @param client Param input.
   * @param id     param input.
   * @return Client.
   */
  @Override
  public Mono<Client> updateClient(Client client, String id) {
    return clientRepository.save(client);
  }

  /**
   * Delete client for id.
   *
   * @param id Param input.
   * @return Client.
   */
  @Override
  public Mono<Client> deleteClient(String id) {
    Mono<Client> client = findById(id);
    Consumer<String> consumer = clientRepository::deleteById;
    consumer.accept(id);
    return client;
  }
}
