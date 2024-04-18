package com.bootcamp51.microservices.clientservice;

import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.model.ProductSales;
import com.bootcamp51.microservices.repository.ClientRepository;
import com.bootcamp51.microservices.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImplTest {

  @InjectMocks
  private ClientServiceImpl service;

  @Mock
  private ClientRepository clientRepository;

  Client client1;
  Client client2;
  List<Client> clientList;
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    //ReflectionTestUtils.setField(service, "clientRepository", clientRepository);
    ProductSales productSales = new ProductSales();
    productSales.setNumAccount("200-1234567890");

    client1 = new Client();
    client1.setId("456");
    client1.setName("Manolo Rojas");
    client1.setNumDocument("12345678");
    client1.setProducts(new ArrayList<>());
    client1.getProducts().add(productSales);


    client2 = new Client();
    client2.setId("123");
    client1.setName("JB");

    clientList = new ArrayList<>();
    clientList.add(client1);
  }


  @Test
  public void findAllClient() throws Exception {
    Mockito.when(clientRepository.findAll()).thenReturn((Flux.just(client1, client2)));
    StepVerifier.create(service.findAllClient())
        .expectNextMatches(e -> e.getId().equals("456"))
        .expectNextMatches(e -> e.getId().equals("123"))
        .verifyComplete();
  }



  @Test
  public void findByName() throws Exception {
    Mockito.when(clientRepository.findByName(Mockito.anyString())).thenReturn((Flux.just(clientList)));
    StepVerifier.create(service.findByName("Manolo"))
        .expectNextMatches(
            e -> e.get(0).getId().equals("456")
        )
        .verifyComplete();
  }

  @Test
  public void findByNumAccount() throws Exception {
    Mockito.when(clientRepository.findByNumAccount(Mockito.anyString())).thenReturn((Mono.just(client1)));
    StepVerifier.create(service.findByNumAccount("Manolo"))
        .expectNextMatches(
            e -> e.getProducts().get(0).getNumAccount().equals("200-1234567890")
        )
        .verifyComplete();
  }



  @Test
  public void findByNumDocument() throws Exception {
    Mockito.when(clientRepository.findByNumDocument(Mockito.anyString())).thenReturn((Mono.just(client1)));
    StepVerifier.create(service.findByNumDocument("12345678"))
        .expectNextMatches(
            e -> e.getNumDocument().equals("12345678")
        )
        .verifyComplete();
  }


  @Test
  public void findById() throws Exception {
    Mockito.when(clientRepository.findById(Mockito.anyString())).thenReturn((Mono.just(client1)));
    StepVerifier.create(service.findById("456"))
        .expectNextMatches(
            e -> e.getId().equals("456")
        )
        .verifyComplete();
  }



  @Test
  public void createClient() throws Exception {
    Mockito.when(clientRepository.save(Mockito.any())).thenReturn((Mono.just(client1)));
    StepVerifier.create(service.createClient(client1))
        .expectNextMatches(
            e -> e.getId().equals("456")
        )
        .verifyComplete();
  }



  @Test
  public void updateClient() throws Exception {
    Mockito.when(clientRepository.save(Mockito.any())).thenReturn((Mono.just(client1)));
    StepVerifier.create(service.updateClient(client1, "456"))
        .expectNextMatches(
            e -> e.getId().equals("456")
        )
        .verifyComplete();
  }


  @Test
  public void deleteClient() throws Exception {
    Mockito.when(clientRepository.findById(Mockito.anyString())).thenReturn((Mono.just(client1)));
    StepVerifier.create(service.deleteClient("456"))
        .expectNextMatches(
            e -> e.getId().equals("456")
        )
        .verifyComplete();
  }








}
