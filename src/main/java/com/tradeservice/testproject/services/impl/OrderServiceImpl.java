package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.repositories.GoodsRepository;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import com.tradeservice.testproject.repositories.OrderRepository;
import com.tradeservice.testproject.services.OrderService;
import com.tradeservice.testproject.utils.MyUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;
  private OrderLineRepository orderLineRepository;
  private GoodsRepository goodsRepository;


  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository,
      OrderLineRepository orderLineRepository, GoodsRepository goodsRepository) {
    this.orderRepository = orderRepository;
    this.orderLineRepository = orderLineRepository;
    this.goodsRepository = goodsRepository;
  }


  @Override
  public Order addOrder(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Order getOrderById(Long id) {
    Optional<Order> optionalOrder = orderRepository.findById(id);
    return optionalOrder.orElse(null);
  }

  @Override
  public void deleteOrder(Long id) {
    orderRepository.deleteById(id);
  }

  @Override
  public Order ediOrder(Order order) {
    return orderRepository.saveAndFlush(order);
  }

  @Override
  public Order getOrderByClient(String client) {
    return orderRepository.findFirstByClient(client);
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }


  @Override
  public OrderLine addOrderLine(OrderLine orderLine) {
    return orderLineRepository.save(orderLine);
  }

  @Override
  public OrderLine getOrderLineById(Long id) {
    Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
    return optionalOrderLine.orElse(null);
  }

  @Override
  public OrderLine editOrderLine(OrderLine orderLine) {
    return orderLineRepository.saveAndFlush(orderLine);
  }

  @Override
  public void deleteOrderLine(Long id) {
    orderLineRepository.deleteById(id);
  }

  @Override
  public OrderLine getOrderLineByOrder(Order orderItem) {
    return orderLineRepository.findByOrderItem(orderItem);
  }

  @Override
  public List<OrderLine> getAllOrderLine() {
    return orderLineRepository.findAll();
  }


  public OrderLine addFullOrder(Map<String, String> map) {
    String client = map.get("client");
    Date date = null;
    try {
      date = new SimpleDateFormat(MyUtil.PATTERN_DATE_FORMAT).parse(map.get("date"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    String address = map.get("address");
    String goodsName = map.get("goodsName");

    int count = 1;
    String strCount = map.get("count");
    if (strCount != null) {
      count = Integer.parseInt(strCount);
    }

    Goods goods = goodsRepository.findByName(goodsName);

    if (!(client == null) && !(address == null) && !(date == null) && !(goods == null)) {
      Order order = new Order(client, date, address);
      OrderLine orderLine = new OrderLine(order, goods, count);
      addOrder(order);
      addOrderLine(orderLine);
      return orderLine;
    }
    return null;
  }

  public OrderLine editFullOrder(Map<String, String> map) { // TODO здесь
//    String client = map.get("client");
//    Date date = null;
//    try {
//      date = new SimpleDateFormat(MyUtil.PATTERN_DATE_FORMAT).parse(map.get("date"));
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }
//
//    String address = map.get("address");
//    String goodsName = map.get("goodsName");
//
//    int count = 1;
//    String strCount = map.get("count");
//    if (strCount != null) {
//      count = Integer.parseInt(strCount);
//    }
//
//    Goods goods = goodsRepository.findByName(goodsName);
//
//    if (!(client == null) && !(address == null) && !(date == null) && !(goods == null)) {
//      Order order = new Order(client, date, address);
//      OrderLine orderLine = new OrderLine(order, goods, count);
//      addOrder(order);
//      addOrderLine(orderLine);
//      return orderLine;
//    }
    return null;
  }

  public OrderLine getOrderLineByOrderId(Long orderId) {
    return orderLineRepository.findByOrderItem(getOrderById(orderId));
  }
}
