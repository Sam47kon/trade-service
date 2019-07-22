package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Order;

public interface OrderService extends CrudService<Order> {

  Order getByClient(String client);
}
