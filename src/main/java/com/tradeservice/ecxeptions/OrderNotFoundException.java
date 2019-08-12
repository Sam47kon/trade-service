package com.tradeservice.ecxeptions;

public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(Long id) {
    super("Такого заказа нет. id: " + id);
  }
}
