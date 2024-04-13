package com.bootcamp51.microservices.service;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.repository.ClientRepository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class ClientService.
 */
public interface ClientService {
  /**
   * Method for findAllClient.
   *
   * @return List Client
   */
  Flux<List<Client>> findAllClient();

  /**
   * Method for findByName client.
   *
   * @param name Param input
   * @return Object Client
   */
  Flux<List<Client>> findByName(String name);

  /**
   * Method for findById client.
   *
   * @param id Param input
   * @return Object Client
   */
  Mono<Client> findById(ObjectId id);

  /**
   * Method for create client.
   *
   * @param client Param input
   * @return Object Client
   */
  Mono<Client> createClient(Client client);

  /**
   * Method for update client.
   *
   * @param client Param input
   * @return Object Client
   */
  Mono<Client> updateClient(Client client, ObjectId id);

  /**
   * Method for delete client.
   *
   * @param id Param input
   * @return Object Client
   */
  Mono<Client> deleteClient(String id);

}
