package com.bootcamp51.microservices.service.impl;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.repository.ClientRepository;
import com.bootcamp51.microservices.service.ClientService;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAllClient() {
        Supplier<List<Client>> supplier =  ()-> clientRepository.findAll();
        return supplier.get();
    }

}
