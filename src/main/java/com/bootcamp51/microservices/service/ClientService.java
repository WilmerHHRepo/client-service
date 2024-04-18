package com.bootcamp51.microservices.service;

import com.bootcamp51.microservices.model.Client;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class ClientService service.
 * author by Wilmer H.
 */
public interface ClientService {
  /**
   * Method for findAllClient.
   *
   * @return List Client.
   */
  Flux<Client> findAllClient();

  /**
   * Method for findByName client.
   *
   * @param name Param input.
   * @return Object Client.
   */
  Flux<List<Client>> findByName(String name);

  /**
   * Method for findByNumAccount client.
   *
   * @param numAccount Param input.
   * @return Object Client.
   */
  Mono<Client> findByNumAccount(String numAccount);

  /**
   * Method for findByNumAccount client.
   *
   * @param numDocument Param input.
   * @return Object Client.
   */
  Mono<Client> findByNumDocument(String numDocument);


  /**
   * Method for findById client.
   *
   * @param id Param input
   * @return Object Client
   */
  Mono<Client> findById(String id);

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
  Mono<Client> updateClient(Client client, String id);

  /**
   * Method for delete client.
   *
   * @param id Param input
   * @return Object Client
   */
  Mono<Client> deleteClient(String id);

}
