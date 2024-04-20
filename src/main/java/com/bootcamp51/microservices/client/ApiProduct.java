package com.bootcamp51.microservices.client;

import com.bootcamp51.microservices.dto.ProductSalesDTO;
import com.bootcamp51.microservices.model.Client;
import com.bootcamp51.microservices.model.ProductSales;
import io.netty.handler.logging.LogLevel;
import javafx.beans.binding.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class ApiProduct {

  @Value("${rutaApiClient:}")
  private String rutaApiClient;

  @Value("${retryApiClient:}")
  private Integer retryApiClient;

  @Value("${timeIntervalApiClient}")
  private Integer timeIntervalApiClient;


  private static final Logger logger = LoggerFactory.getLogger(ApiProduct.class);



  public Mono<Client> createProductSales(String document, ProductSalesDTO productSalesDTO){
    HttpClient httpClient = HttpClient.create()
        .wiretap("",
            LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
    WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
        .baseUrl(rutaApiClient).build();

    return webClient
        .post()
        .uri(uriBuilder -> {
          return uriBuilder
              .path("/sales/" + document)
              .build();
        })
        .body(Mono.just(productSalesDTO), ProductSalesDTO.class)
        .retrieve()
        .bodyToMono(Client.class).map(result -> {
          logger.info("Result client: ===> {}", result);
          return result;
        });


  }




}
