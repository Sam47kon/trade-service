package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.repositories.GoodsRepository;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import com.tradeservice.testproject.repositories.OrderRepository;
import com.tradeservice.testproject.services.OrderService_V2;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl_V2 implements OrderService_V2 {

  private OrderRepository orderRepository;
  private OrderLineRepository orderLineRepository;
  private GoodsRepository goodsRepository;

  @Autowired
  public OrderServiceImpl_V2(OrderRepository orderRepository,
      OrderLineRepository orderLineRepository, GoodsRepository goodsRepository) {
    this.orderRepository = orderRepository;
    this.orderLineRepository = orderLineRepository;
    this.goodsRepository = goodsRepository;
  }

  // добавление полного заказа (OrderLine)
  @Override
  public OrderLine addFullOrder(OrderLine orderLine) {
    Optional<Goods> optionalGoods = goodsRepository.findById(orderLine.getGoods().getGoodsId());
    if (optionalGoods.isEmpty()) {
      throw new NoSuchElementException("Такого товара нет");
    }
    Goods goods = optionalGoods.get();
    orderLine.setGoods(goods);
    Order order = orderLine.getOrderItem();
    orderLine.setOrderItem(addOrder(order));
    return addOrderLine(orderLine);
  }

  // удаление заказа, по его id
  @Override
  public void deleteFullOrder(Long id) {
    OrderLine orderLine = getOrderLineById(id);
    if (orderLine != null) {
      deleteOrderLine(id);
      deleteOrder(orderLine.getOrderItem().getOrderId());
    }
  }

  // изменение заказа, поиск по id OrderLine
  @Override
  public OrderLine editFullOrder(Long id, Order order, Goods goods, Integer count) {
    OrderLine result = getOrderLineById(id);
    result.setOrderItem(order);
    result.setGoods(goods);
    result.setCount(count);
    ediOrder(order);
    return editOrderLine(result);
  }

  @Override
  public OrderLine getOrderLineById(Long id) {
    Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
    OrderLine result = optionalOrderLine.orElse(null);
    if (result == null) {
      throw new NoSuchElementException("Такого Заказа нет");
    }
    return result;
  }

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
