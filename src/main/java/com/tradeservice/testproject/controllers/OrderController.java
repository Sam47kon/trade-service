package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.services.impl.OrderServiceImpl;
import java.util.List;
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
   * 1) - добавление нового заказа // ГОТОВО
   * @param map - {client:?, date:?, address:?, count:?, goodsName:?}. count - default = 1
   * @return map, где все данные по заказу.
   */
  @PostMapping("/neworder")
  @ResponseBody
  public OrderLine addFullOrder(@RequestBody Map<String, String> map) {
    return orderService.addFullOrder(map);
  }

  /**
   * 2)	- изменение существующего заказа // TODO остановился здесь
   *
   * @param map - {client:?, date:?, address:?, count:?, goodsName:?}.
   * @return map, где все данные по заказу.
   */
  @PostMapping("/editorder")
  @ResponseBody
  public OrderLine editFullOrder(@RequestBody Map<String, String> map) {
    return orderService.editFullOrder(map);
  }


  /**
   * 4)	- получение всех заказов  // ГОТОВО
   */
  @GetMapping("/allorders")
  public List<OrderLine> getAllFullOrders() {
    return orderService.getAllOrderLine();
  }


  // получение Order по его id // ГОТОВО
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

  // получение OrderLine по orderId либо по id самого OrderLine // ГОТОВО
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
}
