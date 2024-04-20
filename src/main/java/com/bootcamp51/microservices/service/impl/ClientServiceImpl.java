package com.bootcamp51.microservices.service.impl;

import com.bootcamp51.microservices.client.ApiProduct;
import com.bootcamp51.microservices.constant.ConstantGeneral;
import com.bootcamp51.microservices.dto.ProductSalesDTO;
import com.bootcamp51.microservices.enums.EnumErrorMenssage;
import com.bootcamp51.microservices.enums.EnumProduct;
import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.model.ProductSales;
import com.bootcamp51.microservices.repository.ClientRepository;
import com.bootcamp51.microservices.service.ClientService;
import com.bootcamp51.microservices.constant.ConstantGeneral.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class ClientServiceImpl Service implements.
 * author by Wilmer H.
 */
@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  private final ApiProduct apiProduct;

  private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

  public ClientServiceImpl(ClientRepository clientRepository, ApiProduct apiProduct) {
    this.clientRepository = clientRepository;
    this.apiProduct = apiProduct;
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
      switch (c.getIndTypeClient()) {
        case ConstantGeneral.CODE_PERSON_VIP:
          if (Optional.ofNullable(c.getProducts()).orElse(new ArrayList<>()).size() == ConstantGeneral.MIN_ACCOUNT_PERSON_VIP) {
            ProductSalesDTO productSalesDTO = new ProductSalesDTO();
            Optional<ProductSales> productSavings =   Optional.ofNullable(c.getProducts()).orElse(new ArrayList<>()).stream().filter(p1 ->
                    p1.getIndProduct().equals(EnumProduct.SAVINGS_ACCOUNT.getCode())).findFirst();
            Optional<ProductSales> productCreditCart =   Optional.ofNullable(c.getProducts()).orElse(new ArrayList<>()).stream().filter(p1 ->
                p1.getIndProduct().equals(EnumProduct.CREDIT_CARD.getCode())).findFirst();


            if (productCreditCart.isPresent()) {
              if (productSavings.isPresent()) {

                if (productSavings.get().getAvailableBalance().compareTo(ConstantGeneral.MIN_AMOUNT_COMPANY_PYME) < 0) {
                  logger.warn(EnumErrorMenssage.ERROR1010.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1010.getDescription()).concat(" : ").concat(EnumProduct.SAVINGS_ACCOUNT.getDescription()));
                  throw new RuntimeException(EnumErrorMenssage.ERROR1010.getDescription().concat(" : ").concat(EnumProduct.SAVINGS_ACCOUNT.getDescription()));
                }


                Mono<Client> clientCta = clientRepository.save(client);
                productSalesDTO.setProductSales(productSavings.get());
                Mono<Client> clientMono1 = apiProduct.createProductSales(client.getNumDocument(), productSalesDTO);
                productSalesDTO.setProductSales(productCreditCart.get());
                Mono<Client> clientMono2 = apiProduct.createProductSales(client.getNumDocument(), productSalesDTO);
              } else {
                logger.warn(EnumErrorMenssage.ERROR1006.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1006.getDescription()).concat(" : ").concat(EnumProduct.SAVINGS_ACCOUNT.getDescription()));
                throw new RuntimeException(EnumErrorMenssage.ERROR1006.getDescription().concat(" : ").concat(EnumProduct.SAVINGS_ACCOUNT.getDescription()));
              }
            } else {
              logger.warn(EnumErrorMenssage.ERROR1006.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1006.getDescription()).concat(" : ").concat(EnumProduct.CREDIT_CARD.getDescription()));
              throw new RuntimeException(EnumErrorMenssage.ERROR1006.getDescription().concat(" : ").concat(EnumProduct.CREDIT_CARD.getDescription()));
            }
          } else {
            logger.warn(EnumErrorMenssage.ERROR1008.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1008.getDescription()));
            throw new RuntimeException(EnumErrorMenssage.ERROR1008.getDescription());
          }
          break;
        case ConstantGeneral.CODE_COMPANY_PYME:
          if (Optional.ofNullable(c.getProducts()).orElse(new ArrayList<>()).size() == ConstantGeneral.MIN_ACCOUNT_COMPANY_PYME) {
            ProductSalesDTO productSalesDTO = new ProductSalesDTO();
            Optional<ProductSales> productCurrent =   Optional.ofNullable(c.getProducts()).orElse(new ArrayList<>()).stream().filter(p1 ->
                p1.getIndProduct().equals(EnumProduct.CURRENT_ACCOUNT.getCode())).findFirst();
            Optional<ProductSales> productCreditCart =   Optional.ofNullable(c.getProducts()).orElse(new ArrayList<>()).stream().filter(p1 ->
                p1.getIndProduct().equals(EnumProduct.CREDIT_CARD.getCode())).findFirst();

            if (productCreditCart.isPresent()) {
              if (productCurrent.isPresent()) {
                Mono<Client> clientCta = clientRepository.save(client);
                productSalesDTO.setProductSales(productCurrent.get());
                Mono<Client> clientMono1 = apiProduct.createProductSales(client.getNumDocument(), productSalesDTO);
                productSalesDTO.setProductSales(productCreditCart.get());
                Mono<Client> clientMono2 = apiProduct.createProductSales(client.getNumDocument(), productSalesDTO);
              } else {
                logger.warn(EnumErrorMenssage.ERROR1006.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1006.getDescription()).concat(" : ").concat(EnumProduct.FIXED_TERM_ACCOUNT.getDescription()));
                throw new RuntimeException(EnumErrorMenssage.ERROR1006.getDescription().concat(" : ").concat(EnumProduct.SAVINGS_ACCOUNT.getDescription()));
              }
            } else {
              logger.warn(EnumErrorMenssage.ERROR1006.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1006.getDescription()).concat(" : ").concat(EnumProduct.CREDIT_CARD.getDescription()));
              throw new RuntimeException(EnumErrorMenssage.ERROR1006.getDescription().concat(" : ").concat(EnumProduct.CREDIT_CARD.getDescription()));
            }
          } else {
            logger.warn(EnumErrorMenssage.ERROR1009.getDescription().concat(" - ").concat(EnumErrorMenssage.ERROR1009.getDescription()));
            throw new RuntimeException(EnumErrorMenssage.ERROR1009.getDescription());
          }
          break;
        default:
          clientRepository.save(client);
          break;
      }
      //throw new RuntimeException(EnumErrorMenssage.ERROR1008.getDescription());
    });
    return clientRepository.findByNumDocument(Objects.requireNonNull(client).getNumDocument());
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
