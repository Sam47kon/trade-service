package com.tradeservice.TestProject.Service;

import com.tradeservice.TestProject.entities.OrderLine;
import java.util.List;

public interface OrderLineService {

  OrderLine addOrderLine(OrderLine goods);

  void delete(Long id);

  OrderLine getByName(String name);

  OrderLine editOrderLine(OrderLine goods);

  List<OrderLine> getAll();
}
