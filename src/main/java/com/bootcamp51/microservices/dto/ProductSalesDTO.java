package com.bootcamp51.microservices.dto;

import com.bootcamp51.microservices.model.JointAccount;
import com.bootcamp51.microservices.model.ProductSales;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSalesDTO {
    private ProductSales productSales;
    private JointAccount jointAccount;
}
