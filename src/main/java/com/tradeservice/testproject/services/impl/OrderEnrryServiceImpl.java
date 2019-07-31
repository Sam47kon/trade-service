package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.ecxeptions.OrderEntryNotFoundException;
import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import com.tradeservice.testproject.repositories.OrderRepository;
import com.tradeservice.testproject.services.GoodsService;
import com.tradeservice.testproject.services.OrderEntryService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderEnrryServiceImpl implements OrderEntryService {

  private OrderRepository orderRepository;
  private OrderLineRepository orderLineRepository;
  private GoodsService goodsService;

  @Autowired
  public OrderEnrryServiceImpl(OrderRepository orderRepository,
      OrderLineRepository orderLineRepository, GoodsService goodsService) {
    this.orderRepository = orderRepository;
    this.orderLineRepository = orderLineRepository;
    this.goodsService = goodsService;
  }


  @Override
  public OrderLine addFullOrder(OrderLine orderLine) {
    Goods goods = goodsService.getById(orderLine.getGoods().getGoodsId());
    orderLine.setGoods(goods);
    Order order = orderLine.getOrderItem();
    orderLine.setOrderItem(addOrder(order));
    return addOrderLine(orderLine);
  }

  @Override
  public OrderLine editFullOrder(OrderLine newOrderLine, Long id) {
    OrderLine result =
        orderLineRepository.findById(id).orElseThrow(() -> new OrderEntryNotFoundException(id));
    Goods goods = goodsService.getById(newOrderLine.getGoods().getGoodsId());
    result.setGoods(goods);
    result.setOrderItem(ediOrder(newOrderLine.getOrderItem()));
    return editOrderLine(result);
  }

  @Override
  public void deleteFullOrder(Long id) { // TODO остановился здесь
    OrderLine orderLine = getOrderLineById(id);
    if (orderLine == null) {
      throw new NoSuchElementException("Такого заказа нет");
    }
    deleteOrderLine(id);
    deleteOrder(orderLine.getOrderItem().getOrderId());
  }

  @Override
  public OrderLine getOrderLineById(Long id) {
    Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
    if (optionalOrderLine.isPresent()) {
      return optionalOrderLine.get();
    }
    throw new NoSuchElementException("Такого Заказа нет");
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

  private Order ediOrder(Order order) {
    return orderRepository.saveAndFlush(order);
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
