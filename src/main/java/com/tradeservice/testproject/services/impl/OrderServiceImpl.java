package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.services.OrderService;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.repositories.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Order add(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteById(id);
  }

  @Override
  public Order getByClient(String client) {
    return orderRepository.findByClient(client);
  }

  @Override
  public Order edit(Order order) {
    return orderRepository.saveAndFlush(order);
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.findAll();
  }
}
