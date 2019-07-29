package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import com.tradeservice.testproject.repositories.OrderRepository;
import com.tradeservice.testproject.services.GoodsService;
import com.tradeservice.testproject.services.OrderService;
import java.util.List;
import java.util.NoSuchElementException;
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


  // добавление полного заказа (OrderLine)
  @Override
  public OrderLine addFullOrder(OrderLine orderLine) {
    Goods goods = goodsService.getById(orderLine.getGoods().getGoodsId());
    orderLine.setGoods(goods);
    Order order = orderLine.getOrderItem();
    orderLine.setOrderItem(addOrder(order));
    return addOrderLine(orderLine);
  }

  // изменение заказа
  @Override
  public OrderLine editFullOrder(OrderLine orderLine) {
    Goods goods = goodsService.getById(orderLine.getGoods().getGoodsId());
    orderLine.setGoods(goods);
    Order order = orderLine.getOrderItem();
    ediOrder(order);
    return editOrderLine(orderLine);
  }

  // удаление заказа, по его id
  @Override
  public void deleteFullOrder(Long id) {
    OrderLine orderLine = getOrderLineById(id);
    if (orderLine == null) {
      throw new NoSuchElementException("Такого заказа нет");
    }
    deleteOrderLine(id);
    deleteOrder(orderLine.getOrderItem().getOrderId());
  }

  //получение заказа по его id
  @Override
  public OrderLine getOrderLineById(Long id) {
    Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
    OrderLine result = optionalOrderLine.orElse(null);
    if (result == null) {
      throw new NoSuchElementException("Такого Заказа нет");
    }
    return result;
  }

  // полоучение всех заказов
  @Override
  public List<OrderLine> getAllOrderLines() {
    return orderLineRepository.findAll();
  }


  private Order addOrder(Order order) {
    return orderRepository.save(order);
  }

  private void deleteOrder(Long id) {
    orderRepository.deleteById(id);
  }

  private void ediOrder(Order order) {
    orderRepository.saveAndFlush(order);
  }

  private OrderLine addOrderLine(OrderLine orderLine) {
    return orderLineRepository.save(orderLine);
  }

  private OrderLine editOrderLine(OrderLine orderLine) {
    return orderLineRepository.saveAndFlush(orderLine);
  }

  private void deleteOrderLine(Long id) {
    orderLineRepository.deleteById(id);
  }

}
