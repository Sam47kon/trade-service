package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import java.util.List;

public interface OrderService_V2 {

  OrderLine addFullOrder(OrderLine orderLine);

  void deleteFullOrder(Long id);

  OrderLine editFullOrder(Long id, Order order, Goods goods, Integer count);

  OrderLine getOrderLineById(Long id);

  List<OrderLine> getAllOrderLines();

}
