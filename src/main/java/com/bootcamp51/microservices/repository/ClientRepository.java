package com.bootcamp51.microservices.repository;

import com.bootcamp51.microservices.model.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, Object> {
    @Query("{'name': {$regex: ?0 } }")
    List<Client> findByName(String name);

    @Query("{'_id': ObjectId(?0)}")
    Client findById(String id);
}
