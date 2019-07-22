package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.OrderLine;

public interface OrderLineService extends CrudService<OrderLine> {

  OrderLine getByCount(Long name);
}
