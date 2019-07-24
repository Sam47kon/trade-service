package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.repositories.GoodsRepository;
import com.tradeservice.testproject.repositories.OrderLineRepository;
import com.tradeservice.testproject.repositories.OrderRepository;
import com.tradeservice.testproject.services.OrderService;
import com.tradeservice.testproject.utils.MyUtil;
import com.tradeservice.testproject.utils.OrderParams;
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
  public Order geOrderById(Long id) {
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
  public Order getByClient(String client) {
    return orderRepository.findByClient(client);
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
  public OrderLine getByCount(Long count) {
    return orderLineRepository.findByCount(count);
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
  public OrderLine getOrderLineById(Long id) {
    Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(id);
    return optionalOrderLine.orElse(null);
  }

  @Override
  public List<OrderLine> getAllOrderLine() {
    return orderLineRepository.findAll();
  }


  public String addFullOrder(Map<String, String> map) {
    StringBuilder sb = new StringBuilder();

    String client = map.get(OrderParams.client.toString());
    Date date = null;
    try {
      date = new SimpleDateFormat(MyUtil.PATTERN_DATE_FORMAT)
          .parse(map.get(OrderParams.date.toString()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String address = map.get(OrderParams.address.toString());
    String goodsName = map.get(OrderParams.goodsname.toString());
    int count = Integer.parseInt(map.get(OrderParams.count.toString()));

    Order order = new Order(client, date, address);
    Goods goods = goodsRepository.findByName(goodsName);
    OrderLine orderLine = new OrderLine(order, goods, count);

    if (!(client == null) && !(date == null) && !(address == null) && !(goods == null)) {
      addOrder(order);
      addOrderLine(orderLine);
    }
    sb.append(client).append(date).append(address).append(goodsName);
    return sb.toString();
  }
}
