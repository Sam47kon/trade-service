package com.tradeservice.testproject.ecxeptions;

public class OrderEntryNotFoundException extends RuntimeException {

  public OrderEntryNotFoundException(Long id) {
    super("Такого заказа нет. id: " + id);
  }
}
