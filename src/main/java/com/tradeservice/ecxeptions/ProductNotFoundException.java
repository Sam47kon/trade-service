package com.tradeservice.ecxeptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("Такого товара нет. id:" + id);
  }
}
