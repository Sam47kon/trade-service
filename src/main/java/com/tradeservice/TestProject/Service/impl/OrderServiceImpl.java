package com.tradeservice.TestProject.Service.impl;

import com.tradeservice.TestProject.Service.OrderService;
import com.tradeservice.TestProject.entities.Order;
import com.tradeservice.TestProject.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public Order addOrder(Order goods) {
    return orderRepository.save(goods);
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteById(id);
  }

  @Override
  public Order getByName(String name) {
    return orderRepository.findByName(name);
  }

  @Override
  public Order editOrder(Order goods) {
    return orderRepository.saveAndFlush(goods);
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.findAll();
  }
}
