package com.tradeservice.services.impl;

import com.tradeservice.ecxeptions.OrderEntryNotFoundException;
import com.tradeservice.entities.Goods;
import com.tradeservice.entities.Order;
import com.tradeservice.repositories.OrderLineRepository;
import com.tradeservice.repositories.OrderRepository;
import com.tradeservice.services.GoodsService;
import com.tradeservice.services.OrderService;
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
  public Order add(Order newOrder) {
    Order result = orderRepository
        .save(new Order(newOrder.getClient(), newOrder.getDate(), newOrder.getAddress()));
    addOrderLines(newOrder, result);
    return orderRepository.saveAndFlush(result);
  }

  @Override
  public Order edit(Order newOrder, Long id) {
    Order result =
        orderRepository.findById(id).orElseThrow(() -> new OrderEntryNotFoundException(id));
    result.setClient(newOrder.getClient());
    result.setAddress(newOrder.getAddress());
    result.setDate(newOrder.getDate());
    result.getOrderItems().forEach(orderLine -> orderLineRepository.deleteById(orderLine.getId()));
    addOrderLines(newOrder, result);
    return orderRepository.saveAndFlush(result);
  }

  private void addOrderLines(Order newOrder, Order result) {
    newOrder.getOrderItems().forEach(orderLine -> {
      Goods goods = goodsService.getById(orderLine.getGoods().getGoodsId());
      orderLine.setGoods(goods);
      orderLine.setOrderItem(result);
      orderLineRepository.save(orderLine);
    });
    result.setOrderItems(newOrder.getOrderItems());
  }

  @Override
  public void delete(Long id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new OrderEntryNotFoundException(id));
    order.getOrderItems().forEach(orderLine -> orderLineRepository.deleteById(orderLine.getId()));
    orderRepository.deleteById(id);
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
