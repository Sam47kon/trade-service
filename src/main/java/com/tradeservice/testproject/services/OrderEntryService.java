package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.OrderLine;
import java.util.List;

public interface OrderEntryService {

  OrderLine addFullOrder(OrderLine orderLine);

  OrderLine editFullOrder(OrderLine newOrderLine, Long id);

  void deleteFullOrder(Long id);

  List<OrderLine> getAllOrderLines();

  OrderLine getOrderLineById(Long id);

}
