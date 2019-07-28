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
import java.util.NoSuchElementException;
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
  public void addOrder(Order order) {
    orderRepository.save(order);
  }

  @Override
  public Order getOrderById(Long id) {
    Optional<Order> optionalOrder = orderRepository.findById(id);
    Order result = optionalOrder.orElse(null);
    if (result == null) {
      throw new NoSuchElementException("Такого Order нет");
    }
    return result;
  }

  @Override
  public void deleteOrder(Long id) {
    orderRepository.deleteById(id);
  }

  @Override
  public void ediOrder(Order order) {
    orderRepository.saveAndFlush(order);
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
    OrderLine result = optionalOrderLine.orElse(null);
    if (result == null) {
      throw new NoSuchElementException("Такого Заказа нет");
    }
    return result;
  }

  @Override
  public OrderLine editOrderLine(OrderLine orderLine) {
    return orderLineRepository.saveAndFlush(orderLine);
  }

  @Override
  public void deleteOrderLine(Long id) {
    orderLineRepository.deleteById(id);
  }

//  @Override
//  public OrderLine getOrderLineByOrder(Order orderItem) {
//    return orderLineRepository.findByOrderItem(orderItem);
//  }

  @Override
  public List<OrderLine> getAllOrderLines() {
    return orderLineRepository.findAll();
  }


  // добавление полного заказа (OrderLine)
  public OrderLine addFullOrder(Map<String, String> map) {
    String[] body = {"client", "date", "address", "goodsName"};

    for (String param : body) {
      if (map.get(param) == null) {
        throw new ExceptionInInitializerError("Отсувствует параметр: " + param);
      }
    }

    String strDate = map.get("date");
    Date date;
    try {
      date = new SimpleDateFormat(MyUtil.PATTERN_DATE_FORMAT).parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
      throw new ExceptionInInitializerError("Неверно указана дата! Формат: yyyy/MM/dd");
    }

    String goodsName = map.get("goodsName");
    Goods goods = goodsRepository.findByName(goodsName);
    if (goods == null) {
      throw new NoSuchElementException("Такого товара нет! ");
    }

    String client = map.get("client");
    String address = map.get("address");
    String strCount = map.get("count");
    int count = 1;
    if (strCount != null) {
      count = Integer.parseInt(strCount);
    }

    Order order = new Order(client, date, address);
    OrderLine orderLine = new OrderLine(order, goods, count);
    addOrder(order);
    return addOrderLine(orderLine);
  }

  // удаление заказа, по его id
  public void deleteFullOrder(Long id) {
    OrderLine orderLine = getOrderLineById(id);
    if (orderLine != null) {
      deleteOrderLine(id);
      deleteOrder(orderLine.getOrderItem().getOrderId());
    }
  }

  // изменение заказа, поиск по id OrderLine
  public OrderLine editFullOrder(Map<String, String> map) {
    String strId = map.get("id");
    if (strId == null) {
      return null;
    }

    Long id = Long.parseLong(strId);
    OrderLine orderLine = getOrderLineById(id); // throws NoSuchElementException("Такого Order нет")

    Order order = orderLine.getOrderItem();
    String client = map.get("client");
    if (client != null) {
      order.setClient(client);
    }

    String strDate = map.get("date");
    if (strDate != null) {
      Date date = null;
      try {
        date = new SimpleDateFormat(MyUtil.PATTERN_DATE_FORMAT).parse(strDate);
      } catch (ParseException e) {
        System.out.println("Дата не изменена! Неверно указана дата! Формат: yyyy/MM/dd");
        e.printStackTrace();
      }
      if (date != null) {
        order.setDate(date);
      }
    }

    String address = map.get("address");
    if (address != null) {
      order.setAddress(address);
    }

    int count;
    String strCount = map.get("count");
    if (strCount != null) {
      count = Integer.parseInt(strCount);
      orderLine.setCount(count);
    }

    String goodsName = map.get("goodsName");
    if (goodsName != null) {
      Goods goods = goodsRepository.findByName(goodsName);
      if (goods != null) {
        orderLine.setGoods(goods);
      } else {
        throw new NoSuchElementException("Товар не найден");
      }
    }
    ediOrder(order);
    return editOrderLine(orderLine);
  }
}
