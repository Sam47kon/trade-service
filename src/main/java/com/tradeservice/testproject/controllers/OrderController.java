package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.services.impl.OrderServiceImpl;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {

  private OrderServiceImpl orderService;

  @Autowired
  public OrderController(OrderServiceImpl orderService) {
    this.orderService = orderService;
  }


  /**
   * 1) - добавление нового заказа // РАБОТАЕТ!!!
   * @param map - client, date, address, count, goodsname LOOK THIS!!!
   * @return text if ok
   */
  @PostMapping("/neworder")
  @ResponseBody
  public String addFullOrder(@RequestBody Map<String, String> map) {
    return orderService.addFullOrder(map);
  }


  // получение Order по его id // РАББОТАЕТ!!!
  @GetMapping("/getpreorder")
  @ResponseBody
  public Order getOrder(@RequestBody Map<String, String> map) {
    Order resultOrder = null;
    String strId;

    strId = map.get("id");
    if (strId != null) {
      resultOrder = orderService.getOrderById(Long.parseLong(strId));
    } else {
      String client = map.get("client");
      if (client != null) {
        resultOrder = orderService.getOrderByClient(client);
      }
    }
    return resultOrder;
  }

  // получение OrderLine по orderId либо по id самого OrderLine // РАБОТАЕТ!!!
  @GetMapping("/getorderline")
  @ResponseBody
  public OrderLine getOrderLine(@RequestBody Map<String, String> map) {
    OrderLine resultOrderLine = null;

    String strOrderId = map.get("orderId");
    if (strOrderId != null) {
      resultOrderLine = orderService.getOrderLineByOrderId(Long.parseLong(strOrderId));
    } else {
      String strOrderLineId = map.get("id");
      if (strOrderLineId != null) {
        resultOrderLine = orderService.getOrderLineById(Long.parseLong(strOrderLineId));
      }
    }
    return resultOrderLine;
  }

  /**
   * 4)	- получение всех заказов  // РАБОТАЕТ!!!
   */
  @GetMapping("/allorders")
  public Map<Order, OrderLine> getAllFullOrders() {
    return orderService.getAllFullOrders();
  }
}
