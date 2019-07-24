package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.services.impl.OrderServiceImpl;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
   * @param map - client, date, address, count, goodsname LOOK THIS!!!
   * @return text if ok
   */
  @PostMapping("/neworder")
  @ResponseBody
  public String addOrder(@RequestBody Map<String, String> map) {
    return orderService.addFullOrder(map);
  }
}
