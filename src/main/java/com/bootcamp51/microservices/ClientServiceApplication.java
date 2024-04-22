package com.bootcamp51.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class ClientServiceApplication.
 * author by Wilmer H.
 */
@SpringBootApplication
@EnableSwagger2
public class ClientServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientServiceApplication.class, args);
  }
}
