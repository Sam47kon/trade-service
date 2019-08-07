package com.tradeservice.services;

import com.tradeservice.entities.Order;
import com.tradeservice.entities.OrderLine;
import java.util.List;
import java.util.Optional;

public interface OrderService {

  Order add(Order order);

  Order edit(Order newOrder, Long id);

  void delete(Long id);

  List<Order> getAll();

  Optional<Order> getById(Long id);
}
