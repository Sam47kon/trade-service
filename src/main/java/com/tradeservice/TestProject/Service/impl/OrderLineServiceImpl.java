package com.tradeservice.TestProject.Service.impl;

import com.tradeservice.TestProject.Service.OrderLineService;
import com.tradeservice.TestProject.entities.OrderLine;
import com.tradeservice.TestProject.repository.OrderLineRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderLineServiceImpl implements OrderLineService {

  @Autowired
  private OrderLineRepository orderLineRepository;

  @Override
  public OrderLine addOrderLine(OrderLine goods) {
    return orderLineRepository.save(goods);
  }

  @Override
  public void delete(Long id) {
    orderLineRepository.deleteById(id);
  }

  @Override
  public OrderLine getByName(String name) {
    return orderLineRepository.findByName(name);
  }

  @Override
  public OrderLine editOrderLine(OrderLine goods) {
    return orderLineRepository.saveAndFlush(goods);
  }

  @Override
  public List<OrderLine> getAll() {
    return orderLineRepository.findAll();
  }
}
