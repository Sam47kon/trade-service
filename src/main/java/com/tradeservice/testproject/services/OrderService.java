package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.OrderLine;
import java.util.List;

public interface OrderService {

  OrderLine addFullOrder(OrderLine orderLine);

  OrderLine editFullOrder(OrderLine orderLine);

  void deleteFullOrder(Long id);

  List<OrderLine> getAllOrderLines();

  OrderLine getOrderLineById(Long id);

}
