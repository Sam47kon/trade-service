package com.tradeservice.ecxeptions;

public class GoodsNotFoundException extends RuntimeException {

  public GoodsNotFoundException(Long id) {
    super("Такого товара нет. id:" + id);
  }
}
