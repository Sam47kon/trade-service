package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import java.util.List;

public interface OrderService {

  Order addOrder(Order obj);

  Order getOrderById(Long id);

  Order ediOrder(Order obj);

  void deleteOrder(Long id);

  List<Order> getAllOrders();

  Order getOrderByClient(String client);


  OrderLine addOrderLine(OrderLine orderLine);

  OrderLine getOrderLineById(Long id);

  OrderLine editOrderLine(OrderLine orderLine);

  void deleteOrderLine(Long id);

  OrderLine getOrderLineByOrder(Order order_item);

  List<OrderLine> getAllOrderLine();
}
