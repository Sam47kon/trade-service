package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.ecxeptions.OrderEntryNotFoundException;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import com.tradeservice.testproject.repositories.OrderRepository;
import com.tradeservice.testproject.services.GoodsService;
import com.tradeservice.testproject.services.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;
  private OrderLineRepository orderLineRepository;
  private GoodsService goodsService;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository,
      OrderLineRepository orderLineRepository, GoodsService goodsService) {
    this.orderRepository = orderRepository;
    this.orderLineRepository = orderLineRepository;
    this.goodsService = goodsService;
  }


  @Override
  public Order add(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Order edit(Order newOrder, Long id) {

    return orderRepository.saveAndFlush(
        orderLineRepository.findById(id).orElseThrow(() -> new OrderEntryNotFoundException(id))); // TODO 1111
  }

  @Override
  public void delete(Order order) {
    orderRepository.delete(order);
  }

  @Override
  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  @Override
  public Optional<Order> getById(Long id) {
    return orderRepository.findById(id);
  }
}
