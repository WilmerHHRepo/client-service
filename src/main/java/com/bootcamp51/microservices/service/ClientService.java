package com.bootcamp51.microservices.service;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.repository.ClientRepository;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.logging.log4j.util.Supplier;
import org.bson.types.ObjectId;

/**
 * Class ClientService.
 */
@FunctionalInterface
public interface ClientService {

    List<Client> findAllClient();

    default List<Client> findByName(ClientRepository clientRepository, String name){
        Function<String, List<Client>> function = clientRepository::findByName;
        return function.apply(name);
    }

    default Client findById(ClientRepository clientRepository, String id){
        Function<String, Client> function = clientRepository::findById;
        return function.apply(id);
    }

    default Client createClient(ClientRepository clientRepository, Client client){
        Supplier<Client> supplier = () -> clientRepository.save(client);
        return supplier.get();
    }

    default Client updateClient(ClientRepository clientRepository, Client client){
        Supplier<Client> supplier = () -> clientRepository.save(client);
        return supplier.get();
    }

    default Client deleteClient(ClientRepository clientRepository, String id){
        ObjectId objectId = new ObjectId(id);
        Client client = findById(clientRepository, id);
        Consumer<ObjectId> consumer = clientRepository::deleteById;
        consumer.accept(objectId);
        return client;
    }

}
