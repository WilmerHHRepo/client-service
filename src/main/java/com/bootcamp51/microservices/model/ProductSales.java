package com.bootcamp51.microservices.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class ProductSales model.
 * author by Wilmer H.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSales extends Product {

  /**
   * value numAccount.
   */
  private String numAccount;

  /**
   * value desCCI.
   */
  private String desCodeInterbank;

  /**
   * value numAccountCCI.
   */
  private String numAccountCodeInterbank;

  /**
   * value countableBalance.
   */
  private BigDecimal countableBalance;

  /**
   * value availableBalance.
   */
  private BigDecimal availableBalance;

  /**
   * value jointAccount.
   */
  private JointAccount jointAccount;

  /**
   * value movements.
   */
  private List<Movement> movements;
}
