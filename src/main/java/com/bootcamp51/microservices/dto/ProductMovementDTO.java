package com.bootcamp51.microservices.dto;

import com.bootcamp51.microservices.model.Movement;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductMovementDTO {
    private Movement movement;
}
