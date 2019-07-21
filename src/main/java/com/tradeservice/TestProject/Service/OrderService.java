package com.tradeservice.TestProject.Service;

import com.tradeservice.TestProject.entities.Order;
import java.util.List;

public interface OrderService {

  Order addOrder(Order goods);

  void delete(Long id);

  Order getByName(String name);

  Order editOrder(Order goods);

  List<Order> getAll();
}
