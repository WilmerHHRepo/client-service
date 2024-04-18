package com.bootcamp51.microservices.repository;

import com.bootcamp51.microservices.model.Client;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Class ClientRepository Repository.
 * author by Wilmer H.
 */
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
  @Query("{'name': {$regex: ?0 } }")
  Flux<List<Client>> findByName(String name);

  @Query("{'products.numAccount': ?0 }")
  Mono<Client> findByNumAccount(String numAccount);

  @Query("{'numDocument': ?0 }")
  Mono<Client> findByNumDocument(String numDocument);

}
