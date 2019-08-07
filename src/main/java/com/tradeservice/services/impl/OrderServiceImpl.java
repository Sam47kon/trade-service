package com.tradeservice.services.impl;

import com.tradeservice.ecxeptions.GoodsNotFoundException;
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

  private final OrderRepository orderRepository;
  private final OrderLineRepository orderLineRepository;
  private final GoodsService goodsService;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository,
      OrderLineRepository orderLineRepository, GoodsService goodsService) {
    this.orderRepository = orderRepository;
    this.orderLineRepository = orderLineRepository;
    this.goodsService = goodsService;
  }


  @Override
  public Order add(Order newOrderRequest) {
    Order savedOrder = orderRepository.save(
        new Order(newOrderRequest.getClient(), newOrderRequest.getDate(),
            newOrderRequest.getAddress()));

    addOrderLines(newOrderRequest, savedOrder);
    return orderRepository.saveAndFlush(savedOrder);
  }

  @Override
  public Order edit(Order newOrderRequest, Long id) {
    Order savedOrder =
        orderRepository.findById(id).orElseThrow(() -> new OrderEntryNotFoundException(id));
    savedOrder.setClient(newOrderRequest.getClient());
    savedOrder.setAddress(newOrderRequest.getAddress());
    savedOrder.setDate(newOrderRequest.getDate());
    savedOrder.getOrderLines()
        .forEach(orderLine -> orderLineRepository.deleteById(orderLine.getId()));
    addOrderLines(newOrderRequest, savedOrder);
    return orderRepository.saveAndFlush(savedOrder);
  }

  private void addOrderLines(Order newOrderRequest, Order savedOrder) {
    newOrderRequest.getOrderLines().forEach(orderLine -> {
      Long goodsId = orderLine.getGoods().getGoodsId();
      Goods existGoods = goodsService.getById(goodsId)
          .orElseThrow(() -> new GoodsNotFoundException(goodsId));
      orderLine.setGoods(existGoods);
      orderLine.setOrderItem(savedOrder);
      orderLineRepository.save(orderLine);
    });
    savedOrder.setOrderLines(newOrderRequest.getOrderLines());
  }

  @Override
  public void delete(Long id) {
    orderRepository.findById(id).orElseThrow(() -> new OrderEntryNotFoundException(id));
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
