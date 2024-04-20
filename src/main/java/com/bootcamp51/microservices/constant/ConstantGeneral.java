package com.bootcamp51.microservices.constant;

import java.math.BigDecimal;

public class ConstantGeneral {
  public final static String CODE_SAVINGSACCOUNT = "1";
  public final static String CODE_CURRENTACCOUNT = "2";
  public final static String CODE_FIXEDTERMACCOUNT = "3";
  public final static String CODE_PERSONALCREDIT = "4";
  public final static String CODE_COMMERCIALCREDIT = "5";
  public final static String CODE_CREDITCARD = "6";
  public final static String TIME_SONE = "GMT-5:00";
  public final static String FACTOR_NEGATIVE = "-1";
  public final static String FACTOR_POSITIVE = "1";
  public final static String CODE_DEPOSIT = "1";
  public final static String CODE_RETREAT = "2";
  public final static String CODE_PAYMENT = "3";
  public final static String CODE_CONSUMPTION = "4";
  public final static String COLLECTION_CLIENT = "client";

  public final static String CODE_PERSON = "1";
  public final static String CODE_COMPANY = "2";
  public final static String CODE_PERSON_VIP = "3";
  public final static String CODE_COMPANY_PYME = "4";

  public final static Integer MIN_ACCOUNT_PERSON_VIP = 2;
  public final static Integer MIN_ACCOUNT_COMPANY_PYME = 2;

  public final static BigDecimal MIN_AMOUNT_COMPANY_PYME = new BigDecimal("500");
}
