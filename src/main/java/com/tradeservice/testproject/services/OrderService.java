package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import java.util.List;

public interface OrderService {

  Order addOrder(Order obj);

  Order geOrderById(Long id);

  Order ediOrder(Order obj);

  void deleteOrder(Long id);

  List<Order> getAllOrders();

  Order getByClient(String client);


  OrderLine addOrderLine(OrderLine orderLine);

  OrderLine getOrderLineById(Long id);

  OrderLine editOrderLine(OrderLine orderLine);

  void deleteOrderLine(Long id);

  List<OrderLine> getAllOrderLine();

  OrderLine getByCount(Long name);
}
