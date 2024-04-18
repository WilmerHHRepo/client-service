package com.bootcamp51.microservices.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Class Audit model.
 * author by Wilmer H.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Audit {

  /**
   * value userRegister.
   */
  private String userRegister;

  /**
   * value restrationDate.
   */
  private Date restrationDate;

  /**
   * value userModification.
   */
  private String userModification;

  /**
   * value ModificationDate.
   */
  private Date modificationDate;
}
