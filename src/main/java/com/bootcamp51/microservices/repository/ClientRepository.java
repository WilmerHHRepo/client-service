package com.bootcamp51.microservices.repository;

import com.bootcamp51.microservices.model.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;


public interface ClientRepository extends ReactiveMongoRepository<Client, ObjectId> {
    @Query("{'name': {$regex: ?0 } }")
    Flux<List<Client>> findByName(String name);
}
