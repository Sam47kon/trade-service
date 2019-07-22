package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.services.OrderLineService;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineServiceImpl implements OrderLineService {

  private OrderLineRepository orderLineRepository;

  @Autowired
  public OrderLineServiceImpl(OrderLineRepository orderLineRepository) {
    this.orderLineRepository = orderLineRepository;
  }

  @Override
  public OrderLine add(OrderLine orderLine) {
    return orderLineRepository.save(orderLine);
  }

  @Override
  public void delete(Long id) {
    orderLineRepository.deleteById(id);
  }

  @Override
  public OrderLine getByCount(Long count) {
    return orderLineRepository.findByCount(count);
  }

  @Override
  public OrderLine edit(OrderLine orderLine) {
    return orderLineRepository.saveAndFlush(orderLine);
  }

  @Override
  public List<OrderLine> getAll() {
    return orderLineRepository.findAll();
  }
}
