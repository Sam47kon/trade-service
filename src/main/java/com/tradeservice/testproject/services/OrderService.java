package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {

  Order add(Order order);

  Order edit(Order newOrder, Long id);

  void delete(Order order);

  List<Order> getAll();

  Optional<Order> getById(Long id);

}
